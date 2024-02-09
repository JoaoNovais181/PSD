import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionList {
    private List<SocketChannel> connections;
    private ReentrantLock lock;
    
    public ConnectionList()
    {
        this.connections = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public List<SocketChannel> getConnections() {
        this.lock.lock();
        try
        {
            return new ArrayList<>(connections);
        }
        finally
        {
            this.lock.unlock();
        }
    }

    public void addConnection(SocketChannel conn)
    {
        this.lock.lock();
        try
        {
            this.connections.add(conn);
        }
        finally
        {
            this.lock.unlock();
        }
    }

    public void removeConnection(SocketChannel conn)
    {
        this.lock.lock();
        try
        {
            this.connections.remove(conn);
        }
        finally
        {
            this.lock.unlock();
        }
    }

}
