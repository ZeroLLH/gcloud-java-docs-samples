# Use the Official OpenJDK image for a lean production stage of our multi-stage build.
# https://hub.docker.com/_/openjdk
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM openjdk:17-jdk-alpine

# Copy the jar to the production image from the builder stage.
COPY /target/springboot-helloworld-j17-*.jar /springboot-helloworld-j17.jar

# Run the web service on container startup.
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/springboot-helloworld-j17.jar"]
