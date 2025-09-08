#!/usr/bin/env bash
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/token -H 'Content-Type: application/json' -d '{"username":"arup"}' | jq -r .access_token)
curl -s -X POST http://localhost:8080/api/index \
-H "Authorization: Bearer $TOKEN" -H 'Content-Type: application/json' \
-d '{"title":"Doc One","content":"Hello Lucene world","tags":["hello","lucene"]}' | jq