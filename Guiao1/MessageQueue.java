import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {
    private List<byte[]> messages;
    private ReentrantLock readLock, writeLock;
    private Condition availableMessages;
    // private Map<SocketChannel, 

    public MessageQueue()
    {
        this.messages = new ArrayList<>();
        this.readLock = new ReentrantLock();
        this.writeLock = new ReentrantLock();
        this.availableMessages = this.readLock.newCondition();
    }

    public void addMessage(byte[] message)
    {
        this.readLock.lock();
        this.writeLock.lock();
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
            this.writeLock.unlock();
            this.readLock.unlock();
        }
    }

    public byte[] getMessage(byte[] lastMessage)
    {
        byte[] result = null;
        this.readLock.lock();
        try
        {
            while (this.messages.size() == 0 || this.messages.indexOf(lastMessage)+1 >= this.messages.size())
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
            this.readLock.unlock();
        }
        return result;
    }
}
