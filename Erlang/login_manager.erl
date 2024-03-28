-module(login_manager).
-export([start/0, create_account/2, close_account/2, login/2, logout/1, online/0]).

% funcoes de interface

start() ->
  % register(login_manager, spawn(fun() -> loop(#{}) end)).
  register(?MODULE, spawn(fun() -> loop(#{}) end)).

rpc(Request) ->
  ?MODULE ! {Request, self()},
  receive {Res, ?MODULE} -> Res end.

create_account(Username, Passwd) ->
  rpc({create_account, Username, Passwd}). 

close_account(Username, Passwd) -> 
  rpc({close_account, Username, Passwd}). 

login(Username, Passwd) ->
  rpc({login, Username, Passwd}).

logout(Username) ->
  rpc({logout, Username}).

online() ->
  rpc({online}).

% procecsso "servidor"

handle({create_account, Username, Passwd}, Map) ->
  case maps:find(Username, Map) of
    error ->
      {ok, maps:put(Username, {Passwd, true}, Map)};
    _ -> 
      {user_exists, Map}
  end;

handle({close_account, Username, Passwd}, Map) ->
  case maps:find(Username, Map) of
    {ok, {Passwd, _}} -> 
      {ok, maps:remove(Username, Map)};
    _ ->
      {invalid, Map}
  end;

handle({login, Username, Passwd}, Map) ->
  case maps:find(Username, Map) of
    {ok, {Passwd, _}} ->
      {ok, Map};
    _ ->
      {invalid, Map}
  end;

handle({logout, Username}, Map) ->
  case maps:find(Username, Map) of
    {ok, {Passwd, true}} ->
      {ok, maps:update(Username, {Passwd, false}, Map)};
    _ ->
      {ok, Map}
  end;

handle({online}, Map) ->
  {[Username || {Username, {_, true}} <- maps:to_list(Map)] , Map}.

loop(Map) ->
  receive
    {Request, From} -> 
      {Res, NextState} = handle(Request, Map),
      From ! {Res, ?MODULE},
      loop(NextState)
  end.

