FROM openjdk:8-alpine

COPY target/weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar  weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar

CMD ["java", "-jar", "weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar"]
EXPOSE 8080