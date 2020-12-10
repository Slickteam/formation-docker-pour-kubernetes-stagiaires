FROM panga/openjdk-alpine:11-jdk as builder
RUN jlink \
   --verbose \
   --strip-debug \
   --compress 2 \
   --no-header-files \
   --no-man-pages \
   --output /opt/jre-minimal

FROM alpine:3.8
COPY --from=builder /opt/jre-minimal /opt/jre-minimal
ENV LANG=C.UTF-8 \
   PATH=${PATH}:/opt/jre-minimal/bin
COPY build/libs /opt/app/modules
ARG JVM_OPTS
ENV JVM_OPTS=${JVM_OPTS}
CMD java ${JVM_OPTS} \
   --module-path /opt/app/modules \
   --module hello.world.module.main