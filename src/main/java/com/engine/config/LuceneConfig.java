package com.engine.config;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LuceneConfig {
    @Bean
    public Analyzer analyzer() { return new StandardAnalyzer(); }
}