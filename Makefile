APP=clustered-search-0.1.0


.PHONY: build run test docker-up docker-build


build:
mvn -q -DskipTests package


run: build
java -jar target/$(APP).jar


test:
mvn -q test


docker-build:
docker build -t clustered-search:dev .


docker-up:
docker compose up --build