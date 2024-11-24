package fr.app.seni.hopital.aggregat;


import fr.app.seni.core.cqrs.notation_hopital.command.CreateNotationHopitalCommand;
import fr.app.seni.core.cqrs.notation_hopital.command.DeleteNotationHopitalCommand;
import fr.app.seni.core.cqrs.notation_hopital.command.UpdateNotationHopitalCommand;
import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalCreatedEvent;
import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalDeletedEvent;
import fr.app.seni.core.cqrs.notation_hopital.event.NotationHopitalUpdatedEvent;
import fr.app.seni.core.dto.NotationHopitalDto;
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
public class NotationHopitalAggregate {

    @AggregateIdentifier
    private String  id;
    private NotationHopitalDto notationHopital;

    @CommandHandler
    public NotationHopitalAggregate(CreateNotationHopitalCommand command){
        log.info("CreateNotationHopitalCommand : {}", command.getNotationHopital());
        AggregateLifecycle.apply(new NotationHopitalCreatedEvent(
                        command.getId(),
                        command.getNotationHopital()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(NotationHopitalCreatedEvent event){
        log.info("NotationHopitalCreatedEvent occured : {}", event.getNotationHopital());
        this.id = event.getId();
        this.notationHopital = event.getNotationHopital();
    }

    @CommandHandler
    public void updateHandler(UpdateNotationHopitalCommand command){
        log.info("UpdateNotationHopitalCommand : {}", command.getNotationHopital());
        AggregateLifecycle.apply(new NotationHopitalUpdatedEvent(
                        command.getId(),
                        command.getNotationHopital()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(NotationHopitalUpdatedEvent event){
        log.info("NotationHopitalUpdatedEvent occured : {}", event.getNotationHopital());
        this.notationHopital = event.getNotationHopital();
    }

    @CommandHandler
    public void deleteHandler(DeleteNotationHopitalCommand command){
        log.info("DeleteNotationHopitalCommand : {}", command.getId());
        AggregateLifecycle.apply(new NotationHopitalDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(NotationHopitalDeletedEvent event){
        log.info("NotationHopitalDeletedEvent occured : {}", event.getId());
    }
}
