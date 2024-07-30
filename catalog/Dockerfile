FROM maven as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn clean install

FROM build
COPY target target
ENTRYPOINT ["java","-jar","/*.jar"]
