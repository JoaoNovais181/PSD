-module(roomManager).
-export([room/1, start/0]).

room(Pids) ->
  receive
    {enter, Pid} ->
      io:format("user entered~n", []),
      Pid ! {line, {self(), "OIOIOI"}},
      room([Pid | Pids]);
    {line, Data} = Msg ->
      io:format("received ~p~n", [Data]),
      [Pid ! Msg || Pid <- Pids],
      room(Pids);
    {leave, Pid} ->
      io:format("user left~n", []),
      room(Pids -- [Pid])
  end.

start() ->
  io:format("starting room manager~n"),
  register(?MODULE, spawn(fun() -> loop(#{}) end)).

handle({send, Room, Msg}, RoomMap) ->
  case maps:find(Room, RoomMap) of
    {ok, Pid} ->
      Pid ! Msg,
      {RoomMap};
    _ ->
      RoomPid = spawn(fun() -> room([]) end),
      io:format("creating room ~p~n", [Room]),
      RoomPid ! Msg,
      {maps:put(Room, RoomPid, RoomMap)}
  end.

loop(RoomMap) ->
  receive
   {Request, From} -> 
      io:format("received message ~p from ~p~n", [Request, From]),
      {NextState} = handle(Request, RoomMap),
      loop(NextState);
    _ ->
      io:format("unknown message type~n")
  end.
