package com.engine.suggest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuggestService {

    private final StringRedisTemplate redisTemplate;
    private static final String KEY = "search:suggestions";

    public SuggestService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Record a query (increment its popularity)
    public void recordQuery(String query) {
        redisTemplate.opsForZSet().incrementScore(KEY, query.toLowerCase(), 1.0);
    }

    // Fetch top N suggestions by popularity
    public List<String> getTopSuggestions(int limit) {
        return redisTemplate.opsForZSet()
                .reverseRange(KEY, 0, limit - 1)
                .stream()
                .collect(Collectors.toList());
    }

    // Fetch suggestions starting with prefix
    public List<String> getSuggestionsByPrefix(String prefix, int limit) {
        return redisTemplate.opsForZSet()
                .reverseRange(KEY, 0, -1)
                .stream()
                .filter(s -> s.startsWith(prefix.toLowerCase()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
