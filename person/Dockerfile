FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.6.1
ARG JAR_FILE=./target/person-0.6.1.jar
ADD ${JAR_FILE} /opt/person.jar
ENTRYPOINT ["java","-jar","/opt/person.jar"]
EXPOSE 8000