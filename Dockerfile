# Build
FROM maven:3.8.1-openjdk-17-slim AS build
# Copy from project src & pom, to "virtual" folders:
COPY src /home/app/src
COPY pom.xml /home/app

# Run mvn command; creates jar file
RUN mvn -f /home/app/pom.xml --batch-mode --errors --fail-at-end -DskipTests clean package

# Run container with produced jar
FROM openjdk:17-jdk-slim
# Copy produced *.jar, to the set path:
COPY --from=build /home/app/target/*.jar /home/app/app.jar
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
