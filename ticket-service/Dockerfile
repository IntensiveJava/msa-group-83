FROM gradle:8.10.2-jdk17

WORKDIR /app/ticket-service
COPY src ./src
COPY build.gradle settings.gradle ./

RUN gradle clean bootJar -i --stacktrace

#EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/ticket-service/build/libs/ticket-service-0.0.1-SNAPSHOT.jar"]