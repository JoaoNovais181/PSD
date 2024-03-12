package inc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: inc.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class IncGrpc {

  private IncGrpc() {}

  public static final java.lang.String SERVICE_NAME = "inc.Inc";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<inc.Message,
      inc.Message> getIncManyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "incMany",
      requestType = inc.Message.class,
      responseType = inc.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<inc.Message,
      inc.Message> getIncManyMethod() {
    io.grpc.MethodDescriptor<inc.Message, inc.Message> getIncManyMethod;
    if ((getIncManyMethod = IncGrpc.getIncManyMethod) == null) {
      synchronized (IncGrpc.class) {
        if ((getIncManyMethod = IncGrpc.getIncManyMethod) == null) {
          IncGrpc.getIncManyMethod = getIncManyMethod =
              io.grpc.MethodDescriptor.<inc.Message, inc.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "incMany"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  inc.Message.getDefaultInstance()))
              .setSchemaDescriptor(new IncMethodDescriptorSupplier("incMany"))
              .build();
        }
      }
    }
    return getIncManyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IncStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncStub>() {
        @java.lang.Override
        public IncStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncStub(channel, callOptions);
        }
      };
    return IncStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IncBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncBlockingStub>() {
        @java.lang.Override
        public IncBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncBlockingStub(channel, callOptions);
        }
      };
    return IncBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IncFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IncFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IncFutureStub>() {
        @java.lang.Override
        public IncFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IncFutureStub(channel, callOptions);
        }
      };
    return IncFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<inc.Message> incMany(
        io.grpc.stub.StreamObserver<inc.Message> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getIncManyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Inc.
   */
  public static abstract class IncImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return IncGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Inc.
   */
  public static final class IncStub
      extends io.grpc.stub.AbstractAsyncStub<IncStub> {
    private IncStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<inc.Message> incMany(
        io.grpc.stub.StreamObserver<inc.Message> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getIncManyMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Inc.
   */
  public static final class IncBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<IncBlockingStub> {
    private IncBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Inc.
   */
  public static final class IncFutureStub
      extends io.grpc.stub.AbstractFutureStub<IncFutureStub> {
    private IncFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IncFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IncFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_INC_MANY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INC_MANY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.incMany(
              (io.grpc.stub.StreamObserver<inc.Message>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getIncManyMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              inc.Message,
              inc.Message>(
                service, METHODID_INC_MANY)))
        .build();
  }

  private static abstract class IncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IncBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return inc.IncOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Inc");
    }
  }

  private static final class IncFileDescriptorSupplier
      extends IncBaseDescriptorSupplier {
    IncFileDescriptorSupplier() {}
  }

  private static final class IncMethodDescriptorSupplier
      extends IncBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    IncMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IncGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IncFileDescriptorSupplier())
              .addMethod(getIncManyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
