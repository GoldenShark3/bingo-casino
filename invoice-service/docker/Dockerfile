FROM maven:3.8.4-openjdk-17
COPY .. /home/invoice-service/
RUN cd /home/invoice-service && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/invoice-service/target/invoice-service-0.0.1-SNAPSHOT.jar"]