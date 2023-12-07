FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.6.1
ARG JAR_FILE=./target/auth-0.6.1.jar
ADD ${JAR_FILE} /opt/auth.jar
ENTRYPOINT ["java","-jar","/opt/auth.jar"]
EXPOSE 8000