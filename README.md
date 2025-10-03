# **Clustered Search & Indexing Engine**

A high-performance system for **distributed text ingestion, indexing, and search**, powered by **Lucene** and **Spark** *not just yet another search app*.

---

## **Table of Contents**

* [Project Overview](#project-overview)
* [Features](#features)
* [Real-World Applications](#real-world-applications)
* [System Architecture](#system-architecture)
* [Installation](#installation)
* [Usage](#usage)
* [API Endpoints](#api-endpoints)
* [Example Output](#example-output)
* [Caching & Storage](#caching--storage)
* [Database](#database)
* [Monitoring & Scaling](#monitoring--scaling)
* [Docker Deployment](#docker-deployment)
* [Contributing](#contributing)

---

## **Project Overview**

The **Clustered Search & Indexing Engine** integrates:

* **Apache Lucene** for inverted index creation, tokenization, and scoring (BM25, TF-IDF).
* **Apache Spark** for distributed indexing and parallel query execution.
* **Redis** for autocomplete and hot query caching.
* **PostgreSQL** for structured metadata storage and filtering.
* **Kafka connectors** for streaming ingestion.
* **Docker + Kubernetes** for scalable, reproducible deployments.
* **Prometheus + Grafana** for monitoring and visualization.

This engine enables **low-latency, relevance-ranked search** across massive document corpora, scaling seamlessly from small clusters to enterprise-grade deployments.

---

## **Features**

* Distributed **index building** with Spark.
* Support for **phrase queries, fuzzy search, range queries, and highlighting**.
* **Lucene analyzers** for stemming, tokenization, and stopword filtering.
* **REST/gRPC APIs** with OAuth2/JWT authentication.
* **Redis caching** for autocomplete and repeated queries.
* **CDN integration** for edge delivery of popular queries.
* **SQL-based filtering** on metadata via PostgreSQL.
* **Kubernetes auto-scaling** for index/query workers.

---

## **Real-World Applications**

### **1. Enterprise Knowledge Bases**

* Search across millions of **policies, reports, and technical documents**.
* Filter results by **author, department, or publication date**.

### **2. E-Commerce & Retail**

* Power **product search** with full-text + structured filters (price, category).
* Deliver **real-time autocomplete** for customer-facing portals.

### **3. Media & Publishing**

* Drive **news/article portals** with relevance-based search.
* Provide editors with **metadata-driven dashboards**.

### **4. Cloud Services & Logs**

* Search across **multi-region logs**, filter by **timestamp, severity**.
* Combine **Lucene search** with **Postgres aggregations**.

---

## **System Architecture**

```plaintext
[ Document Sources ] --> [ Kafka / Batch Loader ] 
            |
            v
[ Data Ingestion Layer ] --> Tokenization, Stemming, Stopword Removal (Lucene Analyzers)
            |
            v
[ Distributed Indexing Layer (Spark + Lucene) ] --> Sharded & Replicated Index Segments
            |
            v
[ Query Layer ] --> Lucene Search (BM25/TF-IDF) + Spark Coordination
            |
            +--> [ Redis Cache ] --> [ CDN Edge ]
            |
            v
[ API Layer ] --> REST/gRPC Endpoints + Auth + Rate Limiting
            |
            v
[ PostgreSQL Metadata DB ] --> Filters, Analytics
            |
            v
[ Monitoring Layer ] --> Prometheus + Grafana
```

---

## **Installation**

### **Prerequisites**

* Java 11+
* Apache Spark (local or cluster)
* Redis
* PostgreSQL
* Docker & Kubernetes

### **Clone the Repository**

```bash
git clone https://github.com/Arup-Chauhan/Clustered-Search-Indexing-Engine.git
cd Clustered-Search-Indexing-Engine
```

### **Build**

```bash
mvn clean package
```

---

## **Usage**

### **Index Documents**

```bash
spark-submit --class com.engine.Indexer target/search-engine.jar /input/docs
```

### **Query the Engine**

```bash
curl -X POST http://localhost:8080/search -d '{"query": "distributed systems"}'
```

---

## **API Endpoints**

* **POST /search** → Full-text query, ranked results.
* **GET /suggest** → Autocomplete from Redis cache.
* **GET /filter** → Query with Postgres metadata filters.
* **GET /health** → Cluster health check.

---

## **Example Output**

**Search Response:**

```json
{
  "query": "distributed systems",
  "results": [
    { "doc_id": "123", "score": 0.94, "title": "Distributed Systems Primer", "author": "Tanenbaum" },
    { "doc_id": "456", "score": 0.89, "title": "Scalable Search with Spark & Lucene", "author": "Chauhan" }
  ]
}
```

**Filter Query Response:**

```json
{
  "query": "search",
  "filters": { "author": "Chauhan", "year": "2025" },
  "results": [
    { "doc_id": "456", "score": 0.89, "title": "Scalable Search with Spark & Lucene" }
  ]
}
```

---

## **Caching & Storage**

* **Redis** → Hot queries + autocomplete.
* **Lucene** → Inverted index segments.
* **PostgreSQL** → Metadata for filters, analytics, dashboards.

---

## **Database**

* **Indexes:** Lucene segments (disk-based).
* **Metadata:** Stored in PostgreSQL for structured queries.
* **Hybrid Queries:** Combine **Lucene search** (text relevance) with **Postgres filters** (author, timestamp).

### **Sample PostgreSQL Schema**

```sql
CREATE TABLE documents (
    doc_id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100),
    category VARCHAR(100),
    created_at TIMESTAMP DEFAULT NOW(),
    tags TEXT[],
    file_path TEXT NOT NULL
);

CREATE TABLE query_logs (
    id SERIAL PRIMARY KEY,
    query_text VARCHAR(500) NOT NULL,
    execution_time_ms INT,
    results_count INT,
    created_at TIMESTAMP DEFAULT NOW()
);
```

---

## **Monitoring & Scaling**

* **Prometheus** → Collects engine metrics:

  * `search_request_count_total` (queries served)
  * `search_request_latency_ms` (query latency histogram)
  * `documents_indexed_total` (docs indexed)
  * `cache_hit_count_total` / `cache_miss_count_total` (Redis performance)
  * `spark_job_duration_seconds` (Spark worker job durations)
  * `system_cpu_usage_percent`, `system_memory_usage_bytes` (cluster health)

* **Grafana** → Dashboards visualizing:

  * Queries per second (QPS)
  * Latency distribution (p50, p95, p99)
  * Index build throughput
  * Cache hit/miss ratios
  * Spark cluster utilization

* **Kubernetes Autoscaling** (optional) → Horizontal Pod Autoscaler scales query/index workers based on Prometheus metrics.

---

### **Metrics Example (Java + Prometheus Client)**

```java
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;

public class MetricsRegistry {
    static final Counter searchRequests = Counter.build()
        .name("search_request_count_total")
        .help("Total number of search queries.")
        .register();

    static final Histogram queryLatency = Histogram.build()
        .name("search_request_latency_ms")
        .help("Query latency in milliseconds.")
        .register();

    public static void recordSearch(Runnable searchLogic) {
        searchRequests.inc();
        Histogram.Timer timer = queryLatency.startTimer();
        try {
            searchLogic.run();
        } finally {
            timer.observeDuration();
        }
    }
}
```

---

### **Grafana Dashboard Example**

Sample JSON panel for **query latency histograms**:

```json
{
  "title": "Search Query Latency",
  "type": "graph",
  "targets": [
    {
      "expr": "histogram_quantile(0.95, sum(rate(search_request_latency_ms_bucket[5m])) by (le))",
      "legendFormat": "p95 Latency"
    },
    {
      "expr": "histogram_quantile(0.99, sum(rate(search_request_latency_ms_bucket[5m])) by (le))",
      "legendFormat": "p99 Latency"
    }
  ],
  "xaxis": { "mode": "time" },
  "yaxes": [{ "format": "ms" }, { "format": "short" }]
}
```

Other dashboards:

* **Cache Hit Ratio** → `rate(cache_hit_count_total[5m]) / (rate(cache_hit_count_total[5m]) + rate(cache_miss_count_total[5m]))`
* **Indexing Throughput** → `rate(documents_indexed_total[1m])`
* **Cluster Load** → CPU/Memory usage from Prometheus node exporter.

---
<!--
### **Grafana Dashboard Screenshot**

![Grafana Dashboard Example](docs/dashboard.png)

*(Placeholder — add a real screenshot after running your cluster with Prometheus + Grafana)*

--->
## **Docker Deployment**

```bash
docker-compose up --build
```

Verify:

```bash
curl http://localhost:8080/health
```

For Kubernetes:

```bash
kubectl apply -f k8s/Deployment.yaml
```

---

## **Contributing**

Big on contributions! Feel free to improve indexing/query logic, and open a PR.

---
