package fr.app.seni.hopital.aggregat;


import fr.app.seni.core.cqrs.contrat_hopital.command.ChangeContratHopitalStatusCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.CreateContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.DeleteContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.command.UpdateContratHopitalCommand;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalCreatedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalDeletedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalStatusChangedEvent;
import fr.app.seni.core.cqrs.contrat_hopital.event.ContratHopitalUpdatedEvent;
import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.enums.ContratHopitalStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
@NoArgsConstructor
public class ContratHopitalAggregate {

    @AggregateIdentifier
    private String  id;
    private ContratHopitalDto contratHopital;

    @CommandHandler
    public ContratHopitalAggregate(CreateContratHopitalCommand command){
        log.info("CreateContratHopitalCommand : {}", command.getContratHopital());
        AggregateLifecycle.apply(new ContratHopitalCreatedEvent(
                        command.getId(),
                        command.getContratHopital()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(ContratHopitalCreatedEvent event){
        log.info("ContratHopitalCreatedEvent occured : {}", event.getContratHopital());
        this.id = event.getId();
        this.contratHopital = event.getContratHopital();

        AggregateLifecycle.apply(new ContratHopitalStatusChangedEvent(
                event.getId(),
                ContratHopitalStatus.ACTIVATED
        ));
    }

    @CommandHandler
    public void changeStatusHandler(ChangeContratHopitalStatusCommand command){
        log.info("ChangeContratHopitalStatusCommand : {}", command.getStatut());
        AggregateLifecycle.apply(new ContratHopitalStatusChangedEvent(
                command.getId(),
                command.getStatut()
        ));
    }

    @EventSourcingHandler
    public void onStatusChangedEvent(ContratHopitalStatusChangedEvent event){
        log.info("ContratHopitalStatusChangedEvent occured : {}", event.getStatut());
        this.contratHopital.setStatut(event.getStatut());
    }

    @CommandHandler
    public void updateHandler(UpdateContratHopitalCommand command){
        log.info("UpdateProvinceCommand : {}", command.getContratHopital());
        AggregateLifecycle.apply(new ContratHopitalUpdatedEvent(
                        command.getId(),
                        command.getContratHopital()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(ContratHopitalUpdatedEvent event){
        log.info("ContratHopitalUpdatedEvent occured : {}", event.getContratHopital());
        this.contratHopital = event.getContratHopital();
    }

    @CommandHandler
    public void deleteHandler(DeleteContratHopitalCommand command){
        log.info("DeleteContratHopitalCommand : {}", command.getId());
        AggregateLifecycle.apply(new ContratHopitalDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(ContratHopitalDeletedEvent event){
        log.info("ContratHopitalDeletedEvent occured : {}", event.getId());
    }
}
