FROM ubuntu:latest

WORKDIR /app

COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw"]
