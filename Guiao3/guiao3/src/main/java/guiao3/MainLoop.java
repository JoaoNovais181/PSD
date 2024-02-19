package guiao3;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;

public class MainLoop {

    private Selector sel;

    public MainLoop() throws IOException {
        sel = SelectorProvider.provider().openSelector();
    }

    public Observable<ByteBuffer> read(SocketChannel s) {
        return Observable.create(sub -> {
            s.configureBlocking(false);
            s.register(sel, SelectionKey.OP_READ, sub);
        });
    }

    public Observable<SocketChannel> accept(ServerSocketChannel s) {
        return Observable.create(sub -> {
            s.configureBlocking(false);
            SelectionKey clientKey = s.register(sel, SelectionKey.OP_ACCEPT, sub);
        });
    }
    public void run() throws IOException {
        
        while(true) 
        {
            sel.select();
            for(Iterator<SelectionKey> i=sel.selectedKeys().iterator(); i.hasNext(); ) 
            {
                SelectionKey key = i.next();
                
                try
                {
                    if (key.isAcceptable()) {
                        var sub = (ObservableEmitter<SocketChannel>) key.attachment();
                        var s = ((ServerSocketChannel)key.channel()).accept();
                        sub.onNext(s);
                    }
                    if (key.isReadable()) {
                        var sub = (ObservableEmitter<ByteBuffer>) key.attachment();
                        //leitura
                        ByteBuffer buf = ByteBuffer.allocate(256);
                        SocketChannel s = (SocketChannel)key.channel();

                        int r = s.read(buf);
                        if (r < 0)
                        {
                            key.cancel();
                            s.close();
                        }
                        sub.onNext(buf);
                    }
                }
                catch (Exception e)
                {
                    // nada
                }
                    
                i.remove();
            }
        }



    }        
}
