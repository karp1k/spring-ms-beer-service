package guru.springframework.springmsbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringMsBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMsBeerServiceApplication.class, args);
    }

}
