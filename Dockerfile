FROM openjdk:11.0.4-jre-slim
ARG JAR_FILE=target/product-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
VOLUME /tmp
# change the below path/filename to the desired path and name.
ADD src/main/resources/product_data.csv /tmp
ENTRYPOINT ["java","-jar","/app.jar"]