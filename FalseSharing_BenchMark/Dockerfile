FROM adoptopenjdk/openjdk15:jre-15.0.2_7-alpine
RUN apk update && \
    apk add --update perf
ARG JAR_FILE=target/false-sharing-0.0.1-SNAPSHOT-jar-with-dependencies.jar
RUN mkdir /opt/app
COPY ${JAR_FILE} /opt/app/app.jar
ENTRYPOINT ["perf", "stat", "-d"]