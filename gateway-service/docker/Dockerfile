FROM maven:3.8.4-openjdk-17
COPY .. /home/gateway-service/
RUN cd /home/gateway-service && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar"]