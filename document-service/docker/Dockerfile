FROM maven:3.8.4-openjdk-17
COPY .. /home/document-service/
RUN cd /home/document-service && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/document-service/target/document-service-0.0.1-SNAPSHOT.jar"]