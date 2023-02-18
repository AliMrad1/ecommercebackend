FROM openjdk:17

COPY target/ecommercebackend-0.0.1-SNAPSHOT.jar ecommercebackend.jar

ENTRYPOINT ["java", "-jar", "/ecommercebackend.jar"]