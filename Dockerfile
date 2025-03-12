FROM openjdk
WORKDIR /app
COPY /target/Start-1.0-SNAPSHOT.jar /app/start.jar
ENV TOKEN=7961127155:AAGGXoU4RL0GxDoBPjtdrZrqONWquqEyVgI
ENTRYPOINT ["java","-jar","start.jar"]
