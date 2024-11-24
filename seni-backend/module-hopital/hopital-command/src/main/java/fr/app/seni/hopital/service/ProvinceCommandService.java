package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.province.command.CreateProvinceCommand;
import fr.app.seni.core.cqrs.province.command.DeleteProvinceCommand;
import fr.app.seni.core.cqrs.province.command.UpdateProvinceCommand;
import fr.app.seni.core.dto.ProvinceDto;
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
public class ProvinceCommandService {

    private final CommandGateway commandGateway;

    public AggregateCreatedResponse create(ProvinceDto province){
        try {
            String response = commandGateway.sendAndWait(new CreateProvinceCommand(
                    UUID.randomUUID().toString(),
                    province
            ));
            return AggregateCreatedResponse.builder()
                    .id(response)
                    .message("Province créé avec succès")
                    .build();
        }catch (Exception e){
            throw new CustomException("Erreur lors de la création", HttpStatus.BAD_REQUEST);
        }
    }

    public void update(ProvinceDto province){
        try {
            String response = commandGateway.sendAndWait(new UpdateProvinceCommand(
                    province.getIdProvince(),
                    province
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String id){
        try {
            String response = commandGateway.sendAndWait(new DeleteProvinceCommand(id));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la suppression", HttpStatus.BAD_REQUEST);
        }
    }
}
