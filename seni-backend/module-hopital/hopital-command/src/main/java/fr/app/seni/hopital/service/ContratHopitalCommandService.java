package fr.app.seni.hopital.service;

import fr.app.seni.core.cqrs.AggregateCreatedResponse;
import fr.app.seni.core.cqrs.contrat_hopital.command.ChangeContratHopitalStatusCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.CreateContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.DeleteContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.UpdateContratHopitalCommand;
import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.dto.request.ContratStatutRequest;
import fr.app.seni.core.enums.ContratHopitalStatus;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.core.service.ContratHopitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContratHopitalCommandService {

    private final CommandGateway commandGateway;
    private final ContratHopitalService contratHopitalService;

    public AggregateCreatedResponse create(ContratHopitalDto contratHopital){
        if (contratHopital.getHopital() == null ){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }
        if (contratHopital.getReference().isBlank()){
            throw new CustomException("La référence du contrat est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (contratHopital.getDateDebut() == null || contratHopital.getDateFin() == null){
            throw new CustomException("La date de ébut et de fin sont obligatoire", HttpStatus.BAD_REQUEST);
        }
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
        if (contratHopital.getHopital() == null ){
            throw new CustomException("Aucun hopital spécifier", HttpStatus.BAD_REQUEST);
        }
        if (contratHopital.getReference().isBlank()){
            throw new CustomException("La référence du contrat est obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (contratHopital.getDateDebut() == null || contratHopital.getDateFin() == null){
            throw new CustomException("La date de début et de fin sont obligatoire", HttpStatus.BAD_REQUEST);
        }

        if (contratHopital.getDateDebut().equals(contratHopital.getDateFin()) || contratHopital.getDateDebut().isAfter(contratHopital.getDateFin())) {
            throw new CustomException("La date de début doit être anterieur à la date de fin", HttpStatus.BAD_REQUEST);
        }
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

    public void changeStatus(ContratStatutRequest contratStatutRequest){
        if (contratStatutRequest.idContrattHopital().isBlank()){
            throw new CustomException("Le contrat n'est pas specifié", HttpStatus.BAD_REQUEST);
        }

        if (contratStatutRequest.statut() == null){
            throw new CustomException("Le statut n'est pas defini", HttpStatus.BAD_REQUEST);
        }

        if (contratStatutRequest.motif().isBlank()){
            throw new CustomException("Le motif doit être spécifié", HttpStatus.BAD_REQUEST);
        }

        if (contratStatutRequest.statut().equals(ContratHopitalStatus.ACTIVATED)) {
            ContratHopitalDto contratHopitalDto = contratHopitalService.findOne(contratStatutRequest.idContrattHopital());
            if (contratHopitalDto.getDateFin().isBefore(LocalDate.now())) {
                throw new CustomException("Ce contrat est déja arrivé à terme", HttpStatus.BAD_REQUEST);
            }
        }
        try {
            String response = commandGateway.sendAndWait(new ChangeContratHopitalStatusCommand(
                    contratStatutRequest.idContrattHopital(),
                    contratStatutRequest.statut()
            ));
        }catch (Exception e){
            throw new CustomException("Erreur lors de la modification du statut du contrat", HttpStatus.BAD_REQUEST);
        }
    }
}
