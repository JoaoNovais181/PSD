package guiao3;

import java.net.InetSocketAddress;
import java.net.ProtocolFamily;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

import io.reactivex.rxjava3.core.Observable;

public class Main {
    public static void main(String[] args) throws Exception {
        var ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(12345));
        var loop = new MainLoop();
        var ss_obs = loop.accept(ss);
        ss_obs.subscribe(s -> { 
            var s_obs = loop.read(s);
            s_obs.subscribe(bb -> System.out.println("received: "+bb.remaining()));
        });

        loop.run();

        // Observable.just("xyz\n", "a", "bc\n123", "456\n", "def")
        //     .map(s -> StandardCharsets.UTF_8.encode(s))
        //     .flatMap(new LineSplitter())
        //     .subscribe(s -> System.out.println("<"+s+">"));
    }
}