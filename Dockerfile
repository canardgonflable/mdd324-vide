FROM openjdk:11
ADD target/mdd324-0.0.1-SNAPSHOT.jar mdd324-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mdd324-0.0.1-SNAPSHOT.jar"]