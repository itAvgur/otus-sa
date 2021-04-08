FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.5.0
ARG JAR_FILE=./auth-0.5.0.jar
ADD ${JAR_FILE} /opt/auth.jar
ENTRYPOINT ["java","-jar","/opt/auth.jar"]
EXPOSE 8001
