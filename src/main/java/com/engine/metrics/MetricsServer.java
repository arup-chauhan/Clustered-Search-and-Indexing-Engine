package com.engine.metrics;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;


@Component
public class MetricsServer {
    private final Counter indexedDocs;
    public MetricsServer(MeterRegistry registry) {
        this.indexedDocs = Counter.builder("engine_indexed_docs").description("Number of docs indexed").register(registry);
    }
    public void incIndexed() { indexedDocs.increment(); }
}