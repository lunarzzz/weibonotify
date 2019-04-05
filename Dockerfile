FROM openjdk:8-alpine

COPY target/weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar  weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar
COPY target/classes/driver/chromedriver-linux.exe /driver/chromedriver-linux.exe
CMD ["java", "-jar", "weibo-notify-1.0-SNAPSHOT-jar-with-dependencies.jar"]