package guiao4;

import inc.Message;
import inc.Rx3IncGrpc;
import io.grpc.ManagedChannelBuilder;
import io.reactivex.rxjava3.core.Flowable;

public class Client {
    public static void main(String[] args) throws Exception {
        var c = ManagedChannelBuilder.forAddress("localhost", 12345)
                            .usePlaintext()
                            .build();
        var s = Rx3IncGrpc.newRxStub(c);

        s.incMany(Flowable.just(1,2,3)
                .map(n -> Message.newBuilder().setNum(n).build()))
                .map(Message::getNum)
                .blockingSubscribe(n->{
                    System.out.println("Res: " + n);
                });


//        var ss = ServerSocketChannel.open();
//        ss.bind(new InetSocketAddress(12345));
//        var loop = new MainLoop();
//        var ss_obs = loop.accept(ss);
//        ss_obs.subscribe(s -> {
//            var s_obs = loop.read(s);
//            s_obs.subscribe(bb -> System.out.println("received: "+bb.remaining()));
//        });
//
//        loop.run();

        // Observable.just("xyz\n", "a", "bc\n123", "456\n", "def")
        //     .map(s -> StandardCharsets.UTF_8.encode(s))
        //     .flatMap(new LineSplitter())
        //     .subscribe(s -> System.out.println("<"+s+">"));
    }
}