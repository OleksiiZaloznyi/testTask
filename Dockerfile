FROM openjdk:17-alpine
COPY build/libs/testTask-0.0.1-SNAPSHOT.jar test-task-app.jar
ENTRYPOINT ["java", "-jar", "test-task-app.jar"]