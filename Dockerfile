# Alpine Linux with OpenJDK JRE 11
# FROM openjdk:11-jre-alpine

# Alpine Linux with OpenJDK JRE 8
FROM openjdk:alpine

CMD ["/usr/bin/java", "-version"]

# copy fat jar into image
# COPY
