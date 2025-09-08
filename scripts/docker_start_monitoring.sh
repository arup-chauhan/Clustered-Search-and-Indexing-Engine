#!/bin/bash
docker-compose -f monitoring_microservice/docker-compose.yml --env-file .env up -d

#!/bin/bash
set -e

# Go to monitoring folder
cd "$(dirname "$0")/../monitoring_microservice"

# Substitute env vars into prometheus.yml.template
envsubst < prometheus.yml.template > prometheus.yml

# Start monitoring stack with global .env
docker-compose --env-file ../.env up -d
