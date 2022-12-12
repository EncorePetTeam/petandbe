FROM openjdk:11

RUN apt-get update
RUN apt-get upgrade -y

COPY . /app
RUN chmod +x /app/gradlew

WORKDIR /app
RUN ./gradlew build

CMD ["java", "-jar", "build/libs/petandbe-1.0.0.jar"]