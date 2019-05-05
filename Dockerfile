FROM openjdk:13-alpine as BUILD
RUN apk add bash nodejs

COPY . /build

RUN cd /build && ./gradlew assemble && cp /build/build/libs/Cleanstone*.jar /Cleanstone.jar

FROM openjdk:13-alpine
RUN mkdir /data
WORKDIR /data

COPY --from=BUILD Cleanstone.jar /data/Cleanstone.jar

CMD java -jar Cleanstone.jar

EXPOSE 25565/tcp
EXPOSE 19132/udp
EXPOSE 8080/tcp