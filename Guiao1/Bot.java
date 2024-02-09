import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Bot {
    private static String ADDRESS = "localhost";
    private static int PORT = 12345;
    private String message;
    private long readDelay, writeDelay; // in millis

    public Bot(String message, long readDelay, long writeDelay)
    {
        this.message = message;
        this.readDelay = readDelay;
        this.writeDelay = writeDelay;
    }

    private void runReadingThread(SocketChannel s)
    {
        new Thread(() -> {
            ByteBuffer bb = ByteBuffer.allocate(4000);
            try {
                long recv = 0;
                long aux;
                while ((aux = s.read(bb)) > 0)
                {
                    recv += aux;
                    System.out.println("Recebi " + recv + " bytes no total");
                    // System.out.println(new String(bb.array()));
                    bb.clear();
                    Thread.sleep(this.readDelay);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void runWritingThread(SocketChannel s)
    {
        new Thread(() -> {
            try {
                while (true)
                {
                    // ByteBuffer bb = ByteBuffer.wrap(this.message.getBytes());
                    ByteBuffer bb = ByteBuffer.allocate(4000);
                    s.write(bb);
                    // System.out.println("Sent message");
                    bb.clear();
                    Thread.sleep(this.writeDelay);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void run() throws IOException
    {
        InetSocketAddress socketAddress = new InetSocketAddress(ADDRESS, PORT);
        SocketChannel s = SocketChannel.open(socketAddress);
        System.out.println("Connecting to server on port " + PORT);
        this.runReadingThread(s);
        this.runWritingThread(s);
    }

    public static void main(String[] args) throws Exception
    {
        long readDelay = 10000, writeDelay = 100000;
        if (args.length < 1)
            return;
        String message = args[0];
        if (args.length >= 2)
        {
            readDelay = Integer.parseInt(args[1]);
        }
        if (args.length >= 3)
        {
            writeDelay = Integer.parseInt(args[2]);
        }

        Bot bot = new Bot(message, readDelay, writeDelay);
        bot.run();
    }
}
