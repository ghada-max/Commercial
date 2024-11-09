FROM maven:3.9.2 AS build
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests -X

FROM openjdk:18
COPY --from=build /app/target/*.jar .
EXPOSE 8081
# Replace the CMD with a command that keeps the container running
CMD ["java", "-jar", "commercial-0.0.1-SNAPSHOT.jar"]