package fr.app.seni.parametre.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.region.command.CreateRegionCommand;
import fr.app.seni.core.cqrs.region.command.DeleteRegionCommand;
import fr.app.seni.core.cqrs.region.command.UpdateRegionCommand;
import fr.app.seni.core.dto.RegionDto;
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
public class RegionCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(RegionDto region){
        try {
            String response = commandGateway.sendAndWait(new CreateRegionCommand(
                    UUID.randomUUID().toString(),
                    region
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Region créé avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(RegionDto region){
        try {
            String response = commandGateway.sendAndWait(new UpdateRegionCommand(
                    region.getIdRegion(),
                    region
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteRegionCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
