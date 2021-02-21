FROM openjdk:8-jdk-alpine
MAINTAINER Mayur Doshi <mayur.us@gmail.com>
VOLUME /tmp
EXPOSE 5000
ADD target/*.jar emlebi-restservices.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /emlebi-restservices.jar" ]