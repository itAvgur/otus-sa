FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.3.3
ARG JAR_FILE=./lab-0.3.3.jar
ADD ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java","-jar","/opt/app.jar"]
EXPOSE 8000
