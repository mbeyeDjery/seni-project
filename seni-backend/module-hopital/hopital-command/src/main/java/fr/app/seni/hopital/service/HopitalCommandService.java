package fr.app.seni.hopital.service;


import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.hopital.command.ChangeHopitalStatusCommand;
import fr.app.seni.core.cqrs.hopital.command.CreateHopitalCommand;
import fr.app.seni.core.cqrs.hopital.command.UpdateHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.command.DeleteTypeHopitalCommand;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.dto.HopitalStatutRequest;
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
public class HopitalCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(HopitalDto hopitalDto){
        try {
            String response = commandGateway.sendAndWait(new CreateHopitalCommand(
                    UUID.randomUUID().toString(),
                    hopitalDto
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Hopital créé avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(HopitalDto hopitalDto){
        try {
            String response = commandGateway.sendAndWait(new UpdateHopitalCommand(
                    hopitalDto.getIdHopital(),
                    hopitalDto
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String idHopital){
        try {
            String response = commandGateway.sendAndWait(new DeleteTypeHopitalCommand(idHopital));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }

    public void changeStatus(HopitalStatutRequest hopitalStatutRequest) {
        try {
            String response = commandGateway.sendAndWait(new ChangeHopitalStatusCommand(
                    hopitalStatutRequest.idHopital(),
                    hopitalStatutRequest.statut()
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors du chengement de statut", HttpStatus.BAD_REQUEST);
        }
    }
}
