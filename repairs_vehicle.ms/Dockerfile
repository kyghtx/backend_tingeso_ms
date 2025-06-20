FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} repairs-vehicle.ms.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar","./repairs-vehicle.ms.jar"]