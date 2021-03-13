FROM gradle:jdk15 as build-env
WORKDIR /build
COPY . .
RUN gradle assemble

FROM gradle:jdk15
WORKDIR /app
COPY --from=build-env /build/build/libs .
COPY --from=build-env /build/src/main/resources/application.properties .

ENTRYPOINT ["java", "-jar", "./medusa-1.0.0.jar"]
EXPOSE 8080/tcp