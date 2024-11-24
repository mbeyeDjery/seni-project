package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.contrat_hopital.command.CreateContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.DeleteContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.UpdateContratHopitalCommand;
import fr.app.seni.core.dto.ContratHopitalDto;
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
public class ContratHopitalCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(ContratHopitalDto contratHopital){
        try {
            String response = commandGateway.sendAndWait(new CreateContratHopitalCommand(
                    UUID.randomUUID().toString(),
                    contratHopital
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Contrat initié avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de l'initialisation du contrat", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(ContratHopitalDto contratHopital){
        try {
            String response = commandGateway.sendAndWait(new UpdateContratHopitalCommand(
                    contratHopital.getIdContrattHopital(),
                    contratHopital
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteContratHopitalCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
