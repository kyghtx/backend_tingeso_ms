FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} reports-uh.ms.jar
EXPOSE 8093
ENTRYPOINT ["java", "-jar","./reports-uh.ms.jar"]