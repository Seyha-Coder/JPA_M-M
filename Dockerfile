FROM openjdk:21
LABEL authors="MSI-PC"

COPY ./target/testConfig-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "testConfig-0.0.1-SNAPSHOT.jar"]