package com.engine.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "engine")
public class EngineProps {
    private String indexPath = "./data/index";
    private int suggestionLimit = 10;


    public String getIndexPath() { return indexPath; }
    public void setIndexPath(String indexPath) { this.indexPath = indexPath; }
    public int getSuggestionLimit() { return suggestionLimit; }
    public void setSuggestionLimit(int suggestionLimit) { this.suggestionLimit = suggestionLimit; }
}