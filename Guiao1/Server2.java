
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;



public class Server2
{
    public static void main(String[] args) throws Exception
    {
        MessageQueue queue = new MessageQueue();

        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(12345));

        while (true)
        {
            SocketChannel s = ss.accept();

            // Reader Thread
            new Thread(() -> {
                ByteBuffer bb = ByteBuffer.allocate(256);

                try 
                {
                    while(s.read(bb) > 0)
                    {
                        bb.flip();
                        // queue.addMessage(bb.);
                        bb.clear();
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                finally
                {
                }
            }).start();

            // Writer Thread
            new Thread(() -> {
                // byte[]

                try 
                {
                    while()
                    {
                        bb.flip();
                        byte[] msgOrig = bb.array();
                        byte[] msg = new byte[msgOrig.length];
                        System.arraycopy(msgOrig, 0, msg, 0, msgOrig.length);
                        queue.addMessage(msg);
                        bb.clear();
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                finally
                {
                }
            }).start();
        }
    }
}