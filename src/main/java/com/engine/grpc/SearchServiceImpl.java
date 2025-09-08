package com.engine.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.engine.search.LuceneService;
import com.engine.suggest.SuggestService;

import java.util.List;

@GrpcService
public class SearchServiceImpl extends SearchServiceGrpc.SearchServiceImplBase {

    private final LuceneService luceneService;
    private final SuggestService suggestService;

    public SearchServiceImpl(LuceneService luceneService, SuggestService suggestService) {
        this.luceneService = luceneService;
        this.suggestService = suggestService;
    }

    @Override
    public void search(SearchRequest request, StreamObserver<SearchResponse> responseObserver) {
        try {
            // 🔎 perform Lucene search
            List<Hit> hits = luceneService.search(request.getQ(), request.getSize(), request.getOffset());

            // 📌 record the query in Redis
            suggestService.recordQuery(request.getQ());

            SearchResponse response = SearchResponse.newBuilder()
                    .addAllHits(hits)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
