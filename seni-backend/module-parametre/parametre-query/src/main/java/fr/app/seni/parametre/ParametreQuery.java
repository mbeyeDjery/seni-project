package fr.app.seni.parametre;

import fr.app.seni.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Import({ AxonConfig.class })
@ComponentScan(basePackages = {"fr.app.seni.*"})
@EnableJpaRepositories(basePackages = {"fr.app.seni.*"})
@EntityScan(basePackages = {"fr.app.seni.*", "org.axonframework.*"})
public class ParametreQuery {
    public static void main(String[] args) {
        SpringApplication.run(ParametreQuery.class, args);}
}