
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
                        byte[] orig = bb.array(),
                                copy = new byte[orig.length];
                        System.arraycopy(orig, 0, copy, 0, orig.length);
                        queue.addMessage(copy);
                        bb.clear();
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                finally
                {
                    System.out.println("Ending Connection");
                }
            }).start();

            // Writer Thread
            new Thread(() -> {
                byte[] lastMessage = null,
                        readMessage = null;

                try 
                {
                    while((readMessage = queue.getMessage(lastMessage)) != null)
                    {
                        ByteBuffer bb = ByteBuffer.wrap(readMessage);
                        s.write(bb);
                        bb.clear();
                        lastMessage = readMessage;
                    }
                } 
                catch (Exception e) 
                {
                    // System.out.println("Ending Connection");
                        // e.printStackTrace();
                }
                finally
                {
                    System.out.println("Ending Connection");
                }
            }).start();
        }
    }
}