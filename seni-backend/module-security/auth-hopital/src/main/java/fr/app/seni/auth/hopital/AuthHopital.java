package fr.app.seni.auth.hopital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"fr.app.seni.*"})
@ComponentScan(basePackages = {"fr.app.seni.*"})
@EnableJpaRepositories(basePackages = {"fr.app.seni.*"})
public class AuthHopital {
    public static  void main(String[] args){
        SpringApplication.run(AuthHopital.class, args);
    }
}
