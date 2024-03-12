package inc;

import static inc.IncGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by RxGrpc generator",
comments = "Source: inc.proto")
public final class Rx3IncGrpc {
    private Rx3IncGrpc() {}

    public static RxIncStub newRxStub(io.grpc.Channel channel) {
        return new RxIncStub(channel);
    }

    public static final class RxIncStub extends io.grpc.stub.AbstractStub<RxIncStub> {
        private IncGrpc.IncStub delegateStub;

        private RxIncStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = IncGrpc.newStub(channel);
        }

        private RxIncStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = IncGrpc.newStub(channel).build(channel, callOptions);
        }

        @java.lang.Override
        protected RxIncStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new RxIncStub(channel, callOptions);
        }

        public io.reactivex.rxjava3.core.Flowable<inc.Message> incMany(io.reactivex.rxjava3.core.Flowable<inc.Message> rxRequest) {
            return com.salesforce.rx3grpc.stub.ClientCalls.manyToMany(rxRequest,
                new com.salesforce.reactivegrpc.common.Function<io.grpc.stub.StreamObserver<inc.Message>, io.grpc.stub.StreamObserver<inc.Message>>() {
                    @java.lang.Override
                    public io.grpc.stub.StreamObserver<inc.Message> apply(io.grpc.stub.StreamObserver<inc.Message> observer) {
                        return delegateStub.incMany(observer);
                    }
                }, getCallOptions());
        }

    }

    public static abstract class IncImplBase implements io.grpc.BindableService {


        public io.reactivex.rxjava3.core.Flowable<inc.Message> incMany(io.reactivex.rxjava3.core.Flowable<inc.Message> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            inc.IncGrpc.getIncManyMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            inc.Message,
                                            inc.Message>(
                                            this, METHODID_INC_MANY)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.rx3grpc.stub.ServerCalls.prepareError(throwable);
        }

    }

    public static final int METHODID_INC_MANY = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final IncImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(IncImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_INC_MANY:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.rx3grpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<inc.Message>) responseObserver,
                            serviceImpl::incMany, serviceImpl::onErrorMap, serviceImpl.getCallOptions(methodId));
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
