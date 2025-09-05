#!/usr/bin/env bash
set -euo pipefail

echo ">>> Updating package manager"
if command -v brew >/dev/null 2>&1; then
  brew update
else
  sudo apt-get update -y
fi

echo ">>> Install Java 21 + Maven + Gradle"
if command -v brew >/dev/null 2>&1; then
  brew install openjdk@21 maven gradle
else
  sudo apt-get install -y openjdk-21-jdk maven gradle
fi

echo ">>> Install Apache Spark"
brew install apache-spark || sudo apt-get install -y spark

echo ">>> Install Redis"
brew install redis || sudo apt-get install -y redis-server

echo ">>> Install PostgreSQL"
brew install postgresql || sudo apt-get install -y postgresql

echo ">>> Install Kafka"
brew install kafka || sudo apt-get install -y kafka

echo ">>> Install Docker + kind + kubectl + Helm"
brew install docker kind kubectl helm || sudo apt-get install -y docker.io kind kubectl helm

echo ">>> Install Prometheus & Grafana (via brew)"
brew install prometheus grafana || sudo apt-get install -y prometheus grafana

echo ">>> Verify installations"
java -version
mvn -v
spark-shell --version
redis-server --version
psql --version
kafka-topics --version || true
docker --version
kind --version
kubectl version --client
helm version
prometheus --version
grafana-server -v

echo ">>> All dependencies installed successfully!"
