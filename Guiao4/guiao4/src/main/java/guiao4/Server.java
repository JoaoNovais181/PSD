package guiao4;

import io.grpc.ServerBuilder;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerBuilder.forPort(12345)
                .addService(new IncService())
                .build()
                .start()
                .awaitTermination();
    }
}
