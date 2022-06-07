package by.vyshemirski.gambling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GamblingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamblingServiceApplication.class, args);
    }

}
