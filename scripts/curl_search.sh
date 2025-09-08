#!/usr/bin/env bash
curl -s 'http://localhost:8080/api/search?q=hello&size=10&offset=0' | jq