package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.secteur.command.CreateSecteurCommand;
import fr.app.seni.core.cqrs.secteur.command.DeleteSecteurCommand;
import fr.app.seni.core.cqrs.secteur.command.UpdateSecteurCommand;
import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecteurCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(SecteurDto secteur){
        if (secteur.getVille() == null){
            throw new CustomException("Aucune ville spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (secteur.getLibelle().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }
        try {
            String response = commandGateway.sendAndWait(new CreateSecteurCommand(
                    UUID.randomUUID().toString(),
                    secteur
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Secteur créé avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(SecteurDto secteur){
        if (secteur.getVille() == null){
            throw new CustomException("Aucune ville spécifiée", HttpStatus.BAD_REQUEST);
        }

        if (secteur.getLibelle().trim().isBlank()){
            throw new CustomException("Le nom est obligatoire", HttpStatus.BAD_REQUEST);
        }
        try {
            String response = commandGateway.sendAndWait(new UpdateSecteurCommand(
                    secteur.getIdSecteur(),
                    secteur
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteSecteurCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
