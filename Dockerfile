FROM eclipse-temurin:21-jre
WORKDIR /app
ARG JAR=target/clustered-search-0.1.0.jar
COPY ${JAR} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]