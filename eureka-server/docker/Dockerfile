FROM maven:3.8.4-openjdk-17
COPY .. /home/eureka-server/
RUN cd /home/eureka-server && mvn clean package -Dmaven.test.skip
CMD ["java","-jar","/home/eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar"]