FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.5.0
ARG JAR_FILE=./app-0.5.0.jar
ADD ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
EXPOSE 8000
