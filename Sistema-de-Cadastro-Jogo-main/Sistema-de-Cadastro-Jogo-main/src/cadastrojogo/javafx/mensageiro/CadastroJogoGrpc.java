package cadastrojogo.javafx.mensageiro;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.1)",
    comments = "Source: jogos.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CadastroJogoGrpc {

  private CadastroJogoGrpc() {}

  public static final java.lang.String SERVICE_NAME = "cadastrojogo.javafx.mensageiro.CadastroJogo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest,
      cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> getInserirDadosJogoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InserirDadosJogo",
      requestType = cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest.class,
      responseType = cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest,
      cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> getInserirDadosJogoMethod() {
    io.grpc.MethodDescriptor<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest, cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> getInserirDadosJogoMethod;
    if ((getInserirDadosJogoMethod = CadastroJogoGrpc.getInserirDadosJogoMethod) == null) {
      synchronized (CadastroJogoGrpc.class) {
        if ((getInserirDadosJogoMethod = CadastroJogoGrpc.getInserirDadosJogoMethod) == null) {
          CadastroJogoGrpc.getInserirDadosJogoMethod = getInserirDadosJogoMethod =
              io.grpc.MethodDescriptor.<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest, cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InserirDadosJogo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CadastroJogoMethodDescriptorSupplier("InserirDadosJogo"))
              .build();
        }
      }
    }
    return getInserirDadosJogoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CadastroJogoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadastroJogoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadastroJogoStub>() {
        @java.lang.Override
        public CadastroJogoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadastroJogoStub(channel, callOptions);
        }
      };
    return CadastroJogoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CadastroJogoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadastroJogoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadastroJogoBlockingStub>() {
        @java.lang.Override
        public CadastroJogoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadastroJogoBlockingStub(channel, callOptions);
        }
      };
    return CadastroJogoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CadastroJogoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadastroJogoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadastroJogoFutureStub>() {
        @java.lang.Override
        public CadastroJogoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadastroJogoFutureStub(channel, callOptions);
        }
      };
    return CadastroJogoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void inserirDadosJogo(cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest request,
        io.grpc.stub.StreamObserver<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getInserirDadosJogoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CadastroJogo.
   */
  public static abstract class CadastroJogoImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CadastroJogoGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CadastroJogo.
   */
  public static final class CadastroJogoStub
      extends io.grpc.stub.AbstractAsyncStub<CadastroJogoStub> {
    private CadastroJogoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadastroJogoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadastroJogoStub(channel, callOptions);
    }

    /**
     */
    public void inserirDadosJogo(cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest request,
        io.grpc.stub.StreamObserver<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getInserirDadosJogoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CadastroJogo.
   */
  public static final class CadastroJogoBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CadastroJogoBlockingStub> {
    private CadastroJogoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadastroJogoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadastroJogoBlockingStub(channel, callOptions);
    }

    /**
     */
    public cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse inserirDadosJogo(cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getInserirDadosJogoMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CadastroJogo.
   */
  public static final class CadastroJogoFutureStub
      extends io.grpc.stub.AbstractFutureStub<CadastroJogoFutureStub> {
    private CadastroJogoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadastroJogoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadastroJogoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse> inserirDadosJogo(
        cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getInserirDadosJogoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INSERIR_DADOS_JOGO = 0;

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
        case METHODID_INSERIR_DADOS_JOGO:
          serviceImpl.inserirDadosJogo((cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest) request,
              (io.grpc.stub.StreamObserver<cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getInserirDadosJogoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              cadastrojogo.javafx.mensageiro.Jogos.DadosJogoRequest,
              cadastrojogo.javafx.mensageiro.Jogos.DadosJogoResponse>(
                service, METHODID_INSERIR_DADOS_JOGO)))
        .build();
  }

  private static abstract class CadastroJogoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CadastroJogoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return cadastrojogo.javafx.mensageiro.Jogos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CadastroJogo");
    }
  }

  private static final class CadastroJogoFileDescriptorSupplier
      extends CadastroJogoBaseDescriptorSupplier {
    CadastroJogoFileDescriptorSupplier() {}
  }

  private static final class CadastroJogoMethodDescriptorSupplier
      extends CadastroJogoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CadastroJogoMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CadastroJogoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CadastroJogoFileDescriptorSupplier())
              .addMethod(getInserirDadosJogoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
