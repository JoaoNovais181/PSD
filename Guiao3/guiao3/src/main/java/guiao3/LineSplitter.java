package guiao3;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

public class LineSplitter implements Function<ByteBuffer, Observable<String>> {
    private ByteBuffer line = ByteBuffer.allocate(256);
    
    public Observable<String> apply(ByteBuffer bb) {
        var r = new ArrayList<String>();
        while(bb.hasRemaining()) {
            var b = bb.get();
            if (b == '\n' || !line.hasRemaining()) {
                r.add(StandardCharsets.UTF_8.decode(line.flip()).toString());
                line.clear();
                if (b!='\n')
                    line.put(b);
            } else
                line.put(b);
            
        }
        return Observable.fromIterable(r);
    }
}