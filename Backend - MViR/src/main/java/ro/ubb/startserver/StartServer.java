package ro.ubb.startserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@ComponentScan("ro.ubb")
@EntityScan(basePackages = "ro.ubb.model")
@EnableJpaRepositories(basePackages = "ro.ubb.persistence")
public class StartServer {

    public static void main(String[] args) {
        SpringApplication.run(StartServer.class, args);
    }
}