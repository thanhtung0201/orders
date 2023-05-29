FROM openjdk:11
WORKDIR /app
MAINTAINER tunglt20
COPY target/*-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN chmod a+x app.jar
ARG profile=docker
CMD ["java","-jar","app.jar"]