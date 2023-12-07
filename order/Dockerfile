FROM openjdk:8-jdk-alpine
MAINTAINER itAvgur
LABEL version=0.6.1
ARG JAR_FILE=./target/order-0.6.1.jar
ADD ${JAR_FILE} /opt/order.jar
ENTRYPOINT ["java","-jar","/opt/order.jar"]
EXPOSE 8000