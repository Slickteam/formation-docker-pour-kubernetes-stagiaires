FROM adoptopenjdk:11-jdk-hotspot AS builder
WORKDIR /build
COPY mvnw .
COPY .mvn/wrapper .mvn/wrapper
COPY pom.xml .
# This is for windows to replace windows end of line by linux end of line
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline
COPY src/ src
# This is for windows to replace windows end of line by linux end of line
RUN ./mvnw spring-javaformat:apply
RUN ./mvnw package -DskipTests


FROM panga/openjdk-alpine:11-jdk as jre

RUN jlink \
    --add-modules java.sql,java.naming,java.management,java.instrument,java.security.jgss,java.desktop,jdk.unsupported \
    --verbose \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /opt/jre-minimal

FROM alpine:3.8

COPY --from=jre /opt/jre-minimal /opt/jre-minimal

ENV LANG=C.UTF-8 \
    PATH=${PATH}:/opt/jre-minimal/bin

COPY --from=builder /build/target/modules /opt/app/modules

ARG JVM_OPTS
ENV JVM_OPTS=${JVM_OPTS}

EXPOSE 8080

CMD java ${JVM_OPTS} \
    --add-opens java.base/java.lang=spring.core,javassist \
    --module-path /opt/app/modules \
    --module spring.petclinic
