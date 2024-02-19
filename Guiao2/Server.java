import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Server
{

    public static void main(String[] args) throws IOException
    {

        List<SelectionKey> clientKeys = new ArrayList<>();
        SharedState sharedState = new SharedState();
        Selector sel = SelectorProvider.provider().openSelector();
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(12345));
        ss.configureBlocking(false);
        ss.register(sel, SelectionKey.OP_ACCEPT);

        while(true) 
        {
            sel.select();
            for(Iterator<SelectionKey> i=sel.selectedKeys().iterator(); i.hasNext(); ) 
            {
                    SelectionKey key = i.next();
                    
                    try
                    {
                        if (key.isAcceptable())
                        {
                            SocketChannel s = ss.accept();
                            
                            s.configureBlocking(false);
                            SelectionKey clientKey = s.register(sel, SelectionKey.OP_READ);
                            clientKeys.add(clientKey);
                            ChannelVars vars = new ChannelVars();
                            key.attach(vars);
                        }
                        if (key.isReadable())
                        {
                            ByteBuffer buf = ByteBuffer.allocate(256);
                            SocketChannel s = (SocketChannel)key.channel();

                            int r = s.read(buf);
                            if (r < 0)
                            {
                                key.cancel();
                                s.close();
                            }
                            else
                            {
                                buf.flip();
                                byte[] orig = buf.array();
                                byte[] copy = new byte[orig.length];
                                System.arraycopy(orig, 0, copy, 0, orig.length);
                                for (SelectionKey k : clientKeys)
                                {
                                    // k.attach(buf.duplicate());
                                    k.interestOpsOr(SelectionKey.OP_WRITE);
                                }
                                buf.rewind();
                            }
                        }
                        if (key.isWritable())
                        {
                            SocketChannel s = (SocketChannel)key.channel();
                            // ByteBuffer buf = (ByteBuffer)key.attachment();
                            ChannelVars vars = (ChannelVars)key.attachment();
                            ByteBuffer buf = vars.getBuf();

                            if (!buf.hasRemaining())
                            {
                                byte[] bytes = sharedState.getMessage(vars.getReadMessages());
                                
                                if (bytes == null)
                                    key.interestOpsAnd(~SelectionKey.OP_WRITE);

                                buf = ByteBuffer.wrap(bytes);
                            }

                            s.write(buf);
                            
                            // key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                    catch (CancelledKeyException e)
                    {
                        // nada
                    }
                    
                    i.remove();
            }
        }
    }
}
