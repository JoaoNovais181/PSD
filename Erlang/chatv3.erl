-module(chatv3).
-export([start/1, stop/1]).

start(Port) -> spawn(fun() -> server(Port) end).
stop(Server) -> Server ! stop.

server(Port) ->
  {ok, LSock} = gen_tcp:listen(Port, [binary, {active, once}, {packet, line},
                                      {reuseaddr, true}]),
  % Room = spawn(fun()-> roomManager:room([]) end),
  roomManager:start(),
  spawn(fun() -> acceptor(LSock) end),
  receive stop -> ok end.

acceptor(LSock) ->
  {ok, Sock} = gen_tcp:accept(LSock),
  spawn(fun() -> acceptor(LSock) end),
  roomManager ! {{send, "default", {enter, self()}}, self()},
  user(Sock, "default").


user(Sock, Room) ->
  Self = self(),
  io:format("User ~p in room ~p~n", [Self, Room]),
  receive
    {line, {Self, Data}} ->
      inet:setopts(Sock, [{active, once}]),
      gen_tcp:send(Sock, Data),
      user(Sock, Room);
    {line, {_, Data}} ->
      gen_tcp:send(Sock, Data),
      user(Sock, Room);
    {tcp, _, Data} ->
      DataStr = binary_to_list(Data),
      io:format("User received ~p~n", [DataStr]),
      SplitInfo = string:split(DataStr, " "),
      case SplitInfo of
        ["/room", Room2] -> 
          roomManager ! {{send, Room, {leave, self()}}, self()},
          RoomNew = string:trim(Room2),
          roomManager ! {{send, RoomNew, {enter, self()}}, self()},
          user(Sock, RoomNew);
        _ ->
          roomManager ! {{send, Room, {line, {Self, Data}}}, self()},
          user(Sock, Room)
      end;
    {tcp_closed, _} ->
      roomManager ! {{send, Room, {leave, self()}}, self()};
    {tcp_error, _, _} ->
      roomManager ! {{send, Room, {leave, self()}}, self()}
  end.

