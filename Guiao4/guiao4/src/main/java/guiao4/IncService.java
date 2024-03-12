package guiao4;

import inc.IncGrpc;
import inc.Message;
import inc.Rx3IncGrpc;
import io.reactivex.rxjava3.core.Flowable;

public class IncService extends Rx3IncGrpc.IncImplBase {
//    public Single<Message> incOne(Single<Message> request) {
//        return request.map(Message::getNum)
//                .map(n -> n+1)
//                .map(n -> Message.newBuilder().setNum(n).build());
//    }
    public Flowable<Message> incMany(Flowable<Message> request) {
        return request.map(Message::getNum)
                .map(n -> n+1)
                .map(n -> Message.newBuilder().setNum(n).build());
    }

}
