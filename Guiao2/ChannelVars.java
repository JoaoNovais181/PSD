import java.nio.ByteBuffer;

public class ChannelVars {
    private int readMessages;
    private ByteBuffer buf;
    
    public ChannelVars()
    {
        this.readMessages = 0;
        this.buf = null;
    }
    
    public int getReadMessages() {
        return readMessages;
    }
    
    public void setReadMessages(int readMessages) {
        this.readMessages = readMessages;
    }
    
    public ByteBuffer getBuf() {
        return buf;
    }

    public void setBuf(ByteBuffer buf) {
        this.buf = buf;
    }
}
