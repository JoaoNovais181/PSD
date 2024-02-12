import java.util.ArrayList;
import java.util.List;

public class SharedState {
    private List<byte[]> messages;

    public SharedState()
    {
        this.messages = new ArrayList<>();
        // this.sentMessages = new HashMap<>();
    }

    public void addMessage(byte[] message)
    {
        this.messages.add(message);
    }

    public byte[] getMessage(int idx)
    {
        if (idx >= this.messages.size())
            return null;
        return this.messages.get(idx);
    }
}
