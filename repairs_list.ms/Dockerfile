FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} repairs-list.ms.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar","./repairs-list.ms.jar"]