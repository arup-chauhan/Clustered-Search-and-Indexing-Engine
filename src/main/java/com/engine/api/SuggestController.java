package com.engine.api;

import com.engine.suggest.SuggestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suggest")
public class SuggestController {

    private final SuggestService suggestService;

    public SuggestController(SuggestService suggestService) {
        this.suggestService = suggestService;
    }

    @PostMapping("/record")
    public ResponseEntity<?> recordQuery(@RequestParam String q) {
        suggestService.recordQuery(q);
        return ResponseEntity.ok("Recorded: " + q);
    }

    @GetMapping("/top")
    public ResponseEntity<List<String>> topSuggestions(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(suggestService.getTopSuggestions(limit));
    }

    @GetMapping
    public ResponseEntity<List<String>> suggestions(@RequestParam String prefix,
                                                    @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(suggestService.getSuggestionsByPrefix(prefix, limit));
    }
}
