import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MessageQueue {
    private List<ByteBuffer> messages;
    private ReentrantReadWriteLock lock;
    private Condition availableMessages;
    // private Map<SocketChannel, 

    public MessageQueue()
    {
        this.messages = new ArrayList<>();
        this.lock = new ReentrantReadWriteLock();
        this.availableMessages = this.lock.readLock().newCondition();
    }

    public void addMessage(ByteBuffer message)
    {
        this.lock.writeLock().lock();
        try
        {
            this.messages.add(message);
            int r = this.messages.size() - 10000;
            for (int i=0 ; i<r ; i++)
                this.messages.remove(0);
            this.availableMessages.signalAll();
        }
        finally
        {
            this.lock.writeLock().unlock();
        }
    }

    public ByteBuffer getMessage(ByteBuffer lastMessage)
    {
        ByteBuffer result = null;
        this.lock.readLock().lock();
        try
        {
            while (this.messages.indexOf(lastMessage) >= this.messages.size())
                this.availableMessages.await();

            int idx = this.messages.indexOf(lastMessage);
            if (lastMessage == null || idx == -1)
                result = this.messages.get(0);
            else //if (idx < this.messages.size())
            {
                result = this.messages.get(idx+1);
            }
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        finally
        {
            this.lock.readLock().unlock();
        }
        return result;
    }
}
