FROM gradle:7.5-jdk11-focal AS builder
RUN pwd
COPY . ./Auth
WORKDIR ./Auth
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11.0.16-jre-slim
COPY --from=builder /home/gradle/Auth/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar", "-Dspring-boot.run.arguments=—SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}, —SPRING_DATABASE_USERNAME=${SPRING_DATABASE_USERNAME}, —SPRING_DATABASE_PASSWORD=${SPRING_DATABASE_PASSWORD}"]]
