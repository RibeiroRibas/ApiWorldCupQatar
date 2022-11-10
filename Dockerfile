FROM openjdk:17-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} WorldCupQatar.jar
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=8080","-jar","/WorldCupQatar.jar"]