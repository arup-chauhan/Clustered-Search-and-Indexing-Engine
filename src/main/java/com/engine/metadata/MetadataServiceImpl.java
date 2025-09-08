package com.engine.metadata;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

// Import the generated gRPC classes
import com.engine.metadata.MetadataServiceGrpc;
import com.engine.metadata.MetadataRequest;
import com.engine.metadata.MetadataResponse;
import com.engine.metadata.MetadataListRequest;
import com.engine.metadata.MetadataListResponse;

@GrpcService
public class MetadataServiceImpl extends MetadataServiceGrpc.MetadataServiceImplBase {

    @Override
    public void getMetadata(MetadataRequest request, StreamObserver<MetadataResponse> responseObserver) {
        MetadataResponse response = MetadataResponse.newBuilder()
                .setDocumentId(request.getDocumentId())
                .setTitle("Sample Title")
                .setAuthor("System")
                .setCreatedAt("2025-09-07")
                .putExtraFields("source", "Lucene")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listMetadata(MetadataListRequest request, StreamObserver<MetadataListResponse> responseObserver) {
        MetadataResponse m1 = MetadataResponse.newBuilder()
                .setDocumentId("doc1")
                .setTitle("Doc Title 1")
                .setAuthor("Author A")
                .setCreatedAt("2025-09-06")
                .build();

        MetadataResponse m2 = MetadataResponse.newBuilder()
                .setDocumentId("doc2")
                .setTitle("Doc Title 2")
                .setAuthor("Author B")
                .setCreatedAt("2025-09-07")
                .build();

        MetadataListResponse response = MetadataListResponse.newBuilder()
                .addMetadata(m1)
                .addMetadata(m2)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
