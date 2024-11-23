package fr.app.seni.type.hopital.aggregat;


import fr.app.seni.core.cqrs.type_hopital.command.CreateTypeHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.command.DeleteTypeHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.command.UpdateTypeHopitalCommand;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalCreatedEvent;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalDeletedEvent;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalUpdatedEvent;
import fr.app.seni.core.dto.TypeHopitalDto;
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
public class TypeHopitalAggregate {

    @AggregateIdentifier
    private String  id;
    private TypeHopitalDto typeHopitalDto;

    @CommandHandler
    public TypeHopitalAggregate(CreateTypeHopitalCommand command){
        log.info("CreateTypeHopitalCommand : {}", command);
        AggregateLifecycle.apply(new TypeHopitalCreatedEvent(
                        command.getId(),
                        command.getTypeHopitalDto()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(TypeHopitalCreatedEvent event){
        log.info("TypeHopitalCreatedEvent occured : {}", event);
        this.id = event.getId();
        this.typeHopitalDto = event.getTypeHopitalDto();
    }

    @CommandHandler
    public void updateHandler(UpdateTypeHopitalCommand command){
        log.info("UpdateTypeHopitalCommand : {}", command);
        AggregateLifecycle.apply(new TypeHopitalUpdatedEvent(
                        command.getId(),
                        command.getTypeHopitalDto()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(TypeHopitalUpdatedEvent event){
        log.info("TypeHopitalUpdatedEvent occured : {}", event);
        this.typeHopitalDto = event.getTypeHopitalDto();
    }

    @CommandHandler
    public void deleteHandler(DeleteTypeHopitalCommand command){
        log.info("DeleteTypeHopitalCommand : {}", command);
        AggregateLifecycle.apply(new TypeHopitalDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(TypeHopitalDeletedEvent event){
        log.info("TypeHopitalDeletedEvent occured : {}", event);
    }
}
