FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.1
ARG JAR_FILE=target/lab1-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
EXPOSE 8000
