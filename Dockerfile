FROM openjdk:13-alpine
RUN apk add bash nodejs

COPY . /repo

RUN mkdir /data && cd /repo && ./gradlew assemble && cp /repo/build/libs/Cleanstone*.jar /data/Cleanstone.jar

WORKDIR /data
CMD java -jar Cleanstone.jar

EXPOSE 25565/tcp
EXPOSE 19132/udp
EXPOSE 8080/tcp