FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/posts-1.0-SNAPSHOT.jar /app

EXPOSE 8091

CMD ["java", "-jar", "posts-1.0-SNAPSHOT.jar"]