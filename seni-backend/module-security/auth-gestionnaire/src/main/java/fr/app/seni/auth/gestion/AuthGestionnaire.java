package fr.app.seni.auth.gestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"fr.app.seni.*"})
@ComponentScan(basePackages = {"fr.app.seni.*"})
@EnableJpaRepositories(basePackages = {"fr.app.seni.*"})
public class AuthGestionnaire {
    public static  void main(String[] args){
        SpringApplication.run(AuthGestionnaire.class, args);
    }
}