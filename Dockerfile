FROM openjdk:17-jdk-slim
WORKDIR /app
COPY demo-0.0.1-SNAPSHOT.jar app.jar
COPY key1.p12 /app/key1.p12
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
