-module(myqueue).
-export([create/0, enqueue/2, dequeue/1, test/0]).

% create() -> [].
create() -> {[], []}.

% enqueue(Queue, Item) -> lists:append(Queue, [Item]).
% enqueue(Queue, Item) -> Queue ++ [Item].
enqueue({I, O}, Item) -> {O:I, O}.

% dequeue(Queue) ->
%   case Queue of
%     [] -> empty;
%     [H|Tail] -> {Tail, H}
%   end.
dequeue([]) -> empty;
dequeue([H|T]) -> {T, H}.

test() -> 
  Q = create(),
  Q1 = enqueue(Q, 1),
  Q2 = enqueue(Q1, 2),
  {Q3, 1} = dequeue(Q2),
  {Q4, 2} = dequeue(Q3),
  empty = dequeue(Q4),
  ok.
