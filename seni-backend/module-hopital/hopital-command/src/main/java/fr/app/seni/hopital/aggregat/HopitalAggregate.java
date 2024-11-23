package fr.app.seni.hopital.aggregat;


import fr.app.seni.core.cqrs.hopital.command.ChangeHopitalStatusCommand;
import fr.app.seni.core.cqrs.hopital.command.CreateHopitalCommand;
import fr.app.seni.core.cqrs.hopital.command.DeleteHopitalCommand;
import fr.app.seni.core.cqrs.hopital.command.UpdateHopitalCommand;
import fr.app.seni.core.cqrs.hopital.event.HopitalCreatedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalDeletedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalStatusChangedEvent;
import fr.app.seni.core.cqrs.hopital.event.HopitalUpdatedEvent;
import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.enums.HopitalStatus;
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
public class HopitalAggregate {

    @AggregateIdentifier
    private String  id;
    private HopitalDto hopitalDto;

    @CommandHandler
    public HopitalAggregate(CreateHopitalCommand command){
        log.info("CreateHopitalCommand : {}", command.getHopitalDto());
        AggregateLifecycle.apply(new HopitalCreatedEvent(
                        command.getId(),
                        command.getHopitalDto()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(HopitalCreatedEvent event){
        log.info("HopitalCreatedEvent occured : {}", event.getHopitalDto());
        this.id = event.getId();
        this.hopitalDto = event.getHopitalDto();

        AggregateLifecycle.apply(new HopitalStatusChangedEvent(
                event.getId(),
                HopitalStatus.PENDING
        ));
    }

    @CommandHandler
    public void changeHopitalStatusHandler(ChangeHopitalStatusCommand command){
        log.info("ChangeStatusCommand : {}", command.getHopitalStatus());
        AggregateLifecycle.apply(new HopitalStatusChangedEvent(
                        command.getId(),
                        command.getHopitalStatus()
                ));
    }

    @EventSourcingHandler
    public void onHopitalStatusChangedEvent(HopitalStatusChangedEvent event){
        log.info("HopitalStatusChangedEvent occured : {}", event.getHopitalStatus());
        this.hopitalDto.setStatut(event.getHopitalStatus());
    }

    @CommandHandler
    public void updateHandler(UpdateHopitalCommand command){
        log.info("UpdateHopitalCommand : {}", command.getHopitalDto());
        AggregateLifecycle.apply(new HopitalUpdatedEvent(
                command.getId(),
                command.getHopitalDto()
        ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(HopitalUpdatedEvent event){
        log.info("HopitalUpdatedEvent occured : {}", event.getHopitalDto());
        this.hopitalDto = event.getHopitalDto();
    }

    @CommandHandler
    public void deleteHandler(DeleteHopitalCommand command){
        log.info("DeleteHopitalCommand : {}", command.getId());
        AggregateLifecycle.apply(new HopitalDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(HopitalDeletedEvent event){
        log.info("HopitalDeletedEvent occured : {}", event.getId());
    }
}
