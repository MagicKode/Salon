FROM openjdk:17
ADD build/libs/spring-boot-mongodb-base-project-0.0.1-SNAPSHOT.jar app.jar
#ADD target/docker-compose.jar docker-compose.jar
ENTRYPOINT ["java", "-jar", "docker-compose.jar"]