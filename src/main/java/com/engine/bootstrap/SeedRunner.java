package com.engine.bootstrap;


import com.engine.search.LuceneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class SeedRunner implements CommandLineRunner {
    private final LuceneService lucene;
    public SeedRunner(LuceneService lucene) { this.lucene = lucene; }
    @Override public void run(String... args) throws Exception {
        if (System.getenv().getOrDefault("ENGINE_SEED", "false").equalsIgnoreCase("true")) {
            lucene.indexDoc("hello", "Hello World", "This is a seeded doc", java.util.List.of("seed"));
        }
    }
}