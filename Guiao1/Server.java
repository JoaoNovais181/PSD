
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;



public class Server
{
    public static void main(String[] args) throws Exception
    {
        ConnectionList connections = new ConnectionList();

        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.bind(new InetSocketAddress(12345));

        while (true)
        {
            SocketChannel s = ss.accept();
            connections.addConnection(s);

            new Thread(() -> {
                ByteBuffer bb = ByteBuffer.allocate(256);

                try 
                {
                    while(s.read(bb) > 0)
                    {
                        bb.flip();
                        for (var conn : connections.getConnections())
                            conn.write(bb.duplicate()); // nao devemos utilizar o write dentro do lock por ser bloqueante
                        bb.clear();
                    }
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                finally
                {
                    connections.removeConnection(s);
                }
            }).start();
        }
    }
}