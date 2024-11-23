package fr.app.seni.type.hopital;

import fr.app.seni.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AxonConfig.class })
public class TypeHopitalCommand {
    public static void main(String[] args) {SpringApplication.run(TypeHopitalCommand.class, args);}
}
