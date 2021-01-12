FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
#COPY settings.xml .

#RUN ./mvnw install -DskipTests -s settings.xml
RUN ./mvnw install -DskipTests
RUN ls -lsa target

FROM openjdk:8-jdk-alpine
COPY --from=build /workspace/app/target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]