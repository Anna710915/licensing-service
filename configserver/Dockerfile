#stage 1
#Start with a base image containing Java runtime
FROM openjdk:18.0 as build

# Add Maintainer Info
LABEL maintainer="Anna Merkul <anna.merkul@bk.ru>"

# The application's jar file
ARG JAR_FILE=target/configserver-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} application.jar

#unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /application.jar)

#stage 2
#Same Java runtime
FROM openjdk:18.0

#Add volume pointing to /tmp
VOLUME /tmp

#Copy unpackaged application to new container
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","com.optimagrowth.configserver.ConfigserverApplication"]