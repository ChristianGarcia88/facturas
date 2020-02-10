FROM  openjdk:8
ADD  target/facturas-0.0.1-SNAPSHOT.jar facturas.jar
EXPOSE 1234
ENTRYPOINT ["java","-jar", "facturas.jar"]