FROM openjdk:8-jre-alpine
RUN apk add --no-cache bash && apk add --no-cache curl && adduser --system java
WORKDIR /home/java/
USER java
COPY ./target/car-1.0-SNAPSHOT.jar /opt/car-1.0.jar
EXPOSE 8088
CMD ["java","-jar","/opt/car-1.0.jar"]