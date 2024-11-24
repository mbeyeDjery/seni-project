package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.province.command.CreateProvinceCommand;
import fr.app.seni.core.cqrs.province.command.DeleteProvinceCommand;
import fr.app.seni.core.cqrs.province.command.UpdateProvinceCommand;
import fr.app.seni.core.cqrs.ville.command.CreateVilleCommand;
import fr.app.seni.core.cqrs.ville.command.DeleteVilleCommand;
import fr.app.seni.core.cqrs.ville.command.UpdateVilleCommand;
import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.dto.VilleDto;
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
public class VilleCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(VilleDto ville){
        try {
            String response = commandGateway.sendAndWait(new CreateVilleCommand(
                    UUID.randomUUID().toString(),
                    ville
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Ville créée avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(VilleDto ville){
        try {
            String response = commandGateway.sendAndWait(new UpdateVilleCommand(
                    ville.getIdVille(),
                    ville
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteVilleCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
