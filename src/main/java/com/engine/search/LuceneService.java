package com.engine.search;

import com.engine.grpc.Hit;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LuceneService {
    private final IndexManager indexManager;
    private final QueryBuilder qb;
    private final Counter indexedDocsCounter;

    public LuceneService(IndexManager indexManager, QueryBuilder qb) {
        this.indexManager = indexManager;
        this.qb = qb;
        this.indexedDocsCounter = Counter.builder("engine_indexed_documents_total")
                .description("Total number of documents indexed")
                .register(Metrics.globalRegistry);
    }

    public void indexDoc(String id, String title, String content, Collection<String> tags) throws IOException {
        org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
        doc.add(new StringField("id", id, Field.Store.YES));
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));
        if (tags != null) {
            tags.forEach(t -> doc.add(new StringField("tag", t, Field.Store.YES)));
        }
        indexManager.writer().updateDocument(new Term("id", id), doc);
        indexManager.writer().commit();

        // increment metric after successful commit
        indexedDocsCounter.increment();
    }

    public List<Hit> search(String q, int size, int offset) throws Exception {
        try (DirectoryReader reader = DirectoryReader.open(indexManager.writer())) {
            IndexSearcher searcher = new IndexSearcher(reader);
            Query query = qb.build(q, "content");
            TopDocs top = searcher.search(query, offset + size);
            ScoreDoc[] hits = top.scoreDocs;

            List<Hit> out = new ArrayList<>();
            for (int i = offset; i < Math.min(hits.length, offset + size); i++) {
                org.apache.lucene.document.Document d = searcher.doc(hits[i].doc);
                out.add(
                        Hit.newBuilder()
                                .setId(d.get("id"))
                                .setTitle(d.get("title"))
                                .setScore(hits[i].score)
                                .build()
                );
            }
            return out;
        }
    }
}
