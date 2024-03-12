package guiao4;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.concurrent.TimeUnit;

public class Guiao5 {
/**
 * int op1(int i) { Thread.sleep(i); return i+1; }
 * int op2(int i) { return i*2; }
 * int m1() { int i=1; i=op1(i)+1; i=op2(i); return op1(i); }
 */
    Flowable<Integer> op1(int i) {
        return Flowable.just(i).
                delay(i, TimeUnit.SECONDS).
                map(v -> v+1);
    }

    Flowable<Integer> op2(int i) {
        return Flowable.just(i).
                map(val -> val*2);
    }

    Flowable<Integer> m1() {
        int i = 1;
        return Flowable.just(i).
                flatMap(this::op1).
                map(val -> val+1).
                flatMap(this::op2).
                flatMap(this::op1);
    }

    // int m2() { int i=op1(1)+op1(2); return op2(i); }
    Flowable<Integer> m2() {
        return Flowable.zip(op1(1), op1(2), Integer::sum).
                flatMap(this::op2);

    }

    public static void main(String[] args) {
        Guiao5 g = new Guiao5();
        var a = g.m2();
        System.out.println(a.blockingFirst());
    }
}
