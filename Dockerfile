FROM eclipse-temurin:27-jdk AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests
RUN cp target/ad-api-0.0.1-SNAPSHOT.jar application.jar \
    && java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM eclipse-temurin:27-jre

WORKDIR /app

RUN groupadd --system app && useradd --system --gid app app

USER app

COPY --from=build /app/extracted/dependencies/ ./
COPY --from=build /app/extracted/spring-boot-loader/ ./
COPY --from=build /app/extracted/snapshot-dependencies/ ./
COPY --from=build /app/extracted/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]