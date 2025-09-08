package com.engine.metadata;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: MetadataService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class MetadataServiceGrpc {

  private MetadataServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "engine.metadata.MetadataService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.engine.metadata.MetadataRequest,
      com.engine.metadata.MetadataResponse> getGetMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMetadata",
      requestType = com.engine.metadata.MetadataRequest.class,
      responseType = com.engine.metadata.MetadataResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.engine.metadata.MetadataRequest,
      com.engine.metadata.MetadataResponse> getGetMetadataMethod() {
    io.grpc.MethodDescriptor<com.engine.metadata.MetadataRequest, com.engine.metadata.MetadataResponse> getGetMetadataMethod;
    if ((getGetMetadataMethod = MetadataServiceGrpc.getGetMetadataMethod) == null) {
      synchronized (MetadataServiceGrpc.class) {
        if ((getGetMetadataMethod = MetadataServiceGrpc.getGetMetadataMethod) == null) {
          MetadataServiceGrpc.getGetMetadataMethod = getGetMetadataMethod =
              io.grpc.MethodDescriptor.<com.engine.metadata.MetadataRequest, com.engine.metadata.MetadataResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.engine.metadata.MetadataRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.engine.metadata.MetadataResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MetadataServiceMethodDescriptorSupplier("GetMetadata"))
              .build();
        }
      }
    }
    return getGetMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.engine.metadata.MetadataListRequest,
      com.engine.metadata.MetadataListResponse> getListMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMetadata",
      requestType = com.engine.metadata.MetadataListRequest.class,
      responseType = com.engine.metadata.MetadataListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.engine.metadata.MetadataListRequest,
      com.engine.metadata.MetadataListResponse> getListMetadataMethod() {
    io.grpc.MethodDescriptor<com.engine.metadata.MetadataListRequest, com.engine.metadata.MetadataListResponse> getListMetadataMethod;
    if ((getListMetadataMethod = MetadataServiceGrpc.getListMetadataMethod) == null) {
      synchronized (MetadataServiceGrpc.class) {
        if ((getListMetadataMethod = MetadataServiceGrpc.getListMetadataMethod) == null) {
          MetadataServiceGrpc.getListMetadataMethod = getListMetadataMethod =
              io.grpc.MethodDescriptor.<com.engine.metadata.MetadataListRequest, com.engine.metadata.MetadataListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.engine.metadata.MetadataListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.engine.metadata.MetadataListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new MetadataServiceMethodDescriptorSupplier("ListMetadata"))
              .build();
        }
      }
    }
    return getListMetadataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MetadataServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetadataServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetadataServiceStub>() {
        @java.lang.Override
        public MetadataServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetadataServiceStub(channel, callOptions);
        }
      };
    return MetadataServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MetadataServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetadataServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetadataServiceBlockingStub>() {
        @java.lang.Override
        public MetadataServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetadataServiceBlockingStub(channel, callOptions);
        }
      };
    return MetadataServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MetadataServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<MetadataServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<MetadataServiceFutureStub>() {
        @java.lang.Override
        public MetadataServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new MetadataServiceFutureStub(channel, callOptions);
        }
      };
    return MetadataServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Fetch metadata for a single document
     * </pre>
     */
    default void getMetadata(com.engine.metadata.MetadataRequest request,
        io.grpc.stub.StreamObserver<com.engine.metadata.MetadataResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * Fetch paginated list of metadata
     * </pre>
     */
    default void listMetadata(com.engine.metadata.MetadataListRequest request,
        io.grpc.stub.StreamObserver<com.engine.metadata.MetadataListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMetadataMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service MetadataService.
   */
  public static abstract class MetadataServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return MetadataServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service MetadataService.
   */
  public static final class MetadataServiceStub
      extends io.grpc.stub.AbstractAsyncStub<MetadataServiceStub> {
    private MetadataServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetadataServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetadataServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Fetch metadata for a single document
     * </pre>
     */
    public void getMetadata(com.engine.metadata.MetadataRequest request,
        io.grpc.stub.StreamObserver<com.engine.metadata.MetadataResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Fetch paginated list of metadata
     * </pre>
     */
    public void listMetadata(com.engine.metadata.MetadataListRequest request,
        io.grpc.stub.StreamObserver<com.engine.metadata.MetadataListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMetadataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service MetadataService.
   */
  public static final class MetadataServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<MetadataServiceBlockingStub> {
    private MetadataServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetadataServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetadataServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Fetch metadata for a single document
     * </pre>
     */
    public com.engine.metadata.MetadataResponse getMetadata(com.engine.metadata.MetadataRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Fetch paginated list of metadata
     * </pre>
     */
    public com.engine.metadata.MetadataListResponse listMetadata(com.engine.metadata.MetadataListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMetadataMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service MetadataService.
   */
  public static final class MetadataServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<MetadataServiceFutureStub> {
    private MetadataServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetadataServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new MetadataServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Fetch metadata for a single document
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.engine.metadata.MetadataResponse> getMetadata(
        com.engine.metadata.MetadataRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Fetch paginated list of metadata
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.engine.metadata.MetadataListResponse> listMetadata(
        com.engine.metadata.MetadataListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMetadataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_METADATA = 0;
  private static final int METHODID_LIST_METADATA = 1;

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
        case METHODID_GET_METADATA:
          serviceImpl.getMetadata((com.engine.metadata.MetadataRequest) request,
              (io.grpc.stub.StreamObserver<com.engine.metadata.MetadataResponse>) responseObserver);
          break;
        case METHODID_LIST_METADATA:
          serviceImpl.listMetadata((com.engine.metadata.MetadataListRequest) request,
              (io.grpc.stub.StreamObserver<com.engine.metadata.MetadataListResponse>) responseObserver);
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
          getGetMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.engine.metadata.MetadataRequest,
              com.engine.metadata.MetadataResponse>(
                service, METHODID_GET_METADATA)))
        .addMethod(
          getListMetadataMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.engine.metadata.MetadataListRequest,
              com.engine.metadata.MetadataListResponse>(
                service, METHODID_LIST_METADATA)))
        .build();
  }

  private static abstract class MetadataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MetadataServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.engine.metadata.MetadataServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MetadataService");
    }
  }

  private static final class MetadataServiceFileDescriptorSupplier
      extends MetadataServiceBaseDescriptorSupplier {
    MetadataServiceFileDescriptorSupplier() {}
  }

  private static final class MetadataServiceMethodDescriptorSupplier
      extends MetadataServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    MetadataServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (MetadataServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MetadataServiceFileDescriptorSupplier())
              .addMethod(getGetMetadataMethod())
              .addMethod(getListMetadataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
