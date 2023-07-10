FROM maven:3.8.2-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -f pom.xml clean package

FROM adoptopenjdk/openjdk11:alpine

WORKDIR /app

COPY --from=build /app/target/servico-pagamento-0.0.1-SNAPSHOT.jar servico-pagamento.jar

EXPOSE 8080

CMD ["java", "-jar", "servico-pagamento.jar"]
