package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.notation_hopital.command.CreateNotationHopitalCommand;
import fr.app.seni.core.cqrs.notation_hopital.command.DeleteNotationHopitalCommand;
import fr.app.seni.core.cqrs.notation_hopital.command.UpdateNotationHopitalCommand;
import fr.app.seni.core.dto.NotationHopitalDto;
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
public class NotationHopitalCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(NotationHopitalDto notationHopital){
        try {
            String response = commandGateway.sendAndWait(new CreateNotationHopitalCommand(
                    UUID.randomUUID().toString(),
                    notationHopital
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Notation effectuée avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(NotationHopitalDto notationHopital){
        try {
            String response = commandGateway.sendAndWait(new UpdateNotationHopitalCommand(
                    notationHopital.getIdNotation(),
                    notationHopital
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteNotationHopitalCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
