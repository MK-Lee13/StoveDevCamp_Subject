FROM java:8

WORKDIR /

ARG JAR_FILE=./build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["nohup", "java", "-jar", "-Dspring.profiles.active=prod", "-Djava.security.egd=file:/local/./urandom", "/app.jar", "&"]
