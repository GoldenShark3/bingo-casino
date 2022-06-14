FROM maven:3.8.4-openjdk-17
COPY .. /home/gambling-service/
RUN cd /home/gambling-service && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/gambling-service/target/gambling-service-0.0.1-SNAPSHOT.jar"]