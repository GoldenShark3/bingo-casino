FROM maven:3.8.4-openjdk-17
COPY .. /home/user-service/
RUN cd /home/user-service && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/user-service/target/user-service-0.0.1-SNAPSHOT.jar"]