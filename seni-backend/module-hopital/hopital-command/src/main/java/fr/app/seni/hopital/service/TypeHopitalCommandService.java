package fr.app.seni.hopital.service;


import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.type_hopital.command.CreateTypeHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.command.DeleteTypeHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.command.UpdateTypeHopitalCommand;
import fr.app.seni.core.dto.TypeHopitalDto;
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
public class TypeHopitalCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(TypeHopitalDto typeHopitalDto){
        try {
            String response = commandGateway.sendAndWait(new CreateTypeHopitalCommand(
                    UUID.randomUUID().toString(),
                    typeHopitalDto
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Type créé avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(TypeHopitalDto typeHopitalDto){
        try {
            String response = commandGateway.sendAndWait(new UpdateTypeHopitalCommand(
                    typeHopitalDto.getIdTypeHopital(),
                    typeHopitalDto
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String idTypeHopital){
        try {
            String response = commandGateway.sendAndWait(new DeleteTypeHopitalCommand(idTypeHopital));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
