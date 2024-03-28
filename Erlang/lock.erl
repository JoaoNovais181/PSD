-module(lock).
-export([]).

create() -> 
  spawn(fun() -> lockLoop(([],[])) end).

rpc(Lock, Request) ->
  Lock ! {Request, Lock},
  receive {Res, Lock} -> Res end.

init(Lock, Peers) ->
  rpc(Lock, {init, Peers}).

acquire(Lock) -> 
  rpc(Lock, {acquire}).

release(Lock) -> 
  rpc(Lock, {release}).

stop(Lock) ->
  rpc(Lock, {stop}).

% lock code

handle({init, Peers}, (Msgs, CurrentPeers)) ->
  {ok, (Msgs, Peers)}.

handle({acquire}, (Msgs, ))

lockLoop(State) ->
  receive
    {stop, From} -> From ! {ok, self()}. 
    {Request, From} ->
      {Res, NextState} = handle(Request, (State)),
      From ! {Res, self()},
      lockLoop(NextState)
  end.