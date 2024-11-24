package fr.app.seni.parametre.aggregat;


import fr.app.seni.core.cqrs.ville.command.CreateVilleCommand;
import fr.app.seni.core.cqrs.ville.command.DeleteVilleCommand;
import fr.app.seni.core.cqrs.ville.command.UpdateVilleCommand;
import fr.app.seni.core.cqrs.ville.event.VilleCreatedEvent;
import fr.app.seni.core.cqrs.ville.event.VilleDeletedEvent;
import fr.app.seni.core.cqrs.ville.event.VilleUpdatedEvent;
import fr.app.seni.core.dto.VilleDto;
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
public class VilleAggregate {

    @AggregateIdentifier
    private String  id;
    private VilleDto villeDto;

    @CommandHandler
    public VilleAggregate(CreateVilleCommand command){
        log.info("CreateVilleCommand : {}", command.getVille());
        AggregateLifecycle.apply(new VilleCreatedEvent(
                        command.getId(),
                        command.getVille()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(VilleCreatedEvent event){
        log.info("VilleCreatedEvent occured : {}", event.getVille());
        this.id = event.getId();
        this.villeDto = event.getVille();
    }

    @CommandHandler
    public void updateHandler(UpdateVilleCommand command){
        log.info("UpdateVilleCommand : {}", command.getVille());
        AggregateLifecycle.apply(new VilleUpdatedEvent(
                        command.getId(),
                        command.getVille()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(VilleUpdatedEvent event){
        log.info("VilleUpdatedEvent occured : {}", event.getVille());
        this.villeDto = event.getVille();
    }

    @CommandHandler
    public void deleteHandler(DeleteVilleCommand command){
        log.info("DeleteVilleCommand : {}", command.getId());
        AggregateLifecycle.apply(new VilleDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(VilleDeletedEvent event){
        log.info("VilleDeletedEvent occured : {}", event);
    }
}
