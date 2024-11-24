package fr.app.seni.parametre.aggregat;


import fr.app.seni.core.cqrs.region.command.CreateRegionCommand;
import fr.app.seni.core.cqrs.region.command.DeleteRegionCommand;
import fr.app.seni.core.cqrs.region.command.UpdateRegionCommand;
import fr.app.seni.core.cqrs.region.event.RegionCreatedEvent;
import fr.app.seni.core.cqrs.region.event.RegionDeletedEvent;
import fr.app.seni.core.cqrs.region.event.RegionUpdatedEvent;
import fr.app.seni.core.cqrs.type_hopital.event.TypeHopitalDeletedEvent;
import fr.app.seni.core.dto.RegionDto;
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
public class RegionAggregate {

    @AggregateIdentifier
    private String  id;
    private RegionDto region;

    @CommandHandler
    public RegionAggregate(CreateRegionCommand command){
        log.info("CreateRegionCommand : {}", command);
        AggregateLifecycle.apply(new RegionCreatedEvent(
                        command.getId(),
                        command.getRegion()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(RegionCreatedEvent event){
        log.info("RegionCreatedEvent occured : {}", event);
        this.id = event.getId();
        this.region = event.getRegion();
    }

    @CommandHandler
    public void updateHandler(UpdateRegionCommand command){
        log.info("UpdateRegionCommand : {}", command);
        AggregateLifecycle.apply(new RegionUpdatedEvent(
                        command.getId(),
                        command.getRegion()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(RegionUpdatedEvent event){
        log.info("RegionUpdatedEvent occured : {}", event);
        this.region = event.getRegion();
    }

    @CommandHandler
    public void deleteHandler(DeleteRegionCommand command){
        log.info("DeleteRegionCommand : {}", command);
        AggregateLifecycle.apply(new TypeHopitalDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(RegionDeletedEvent event){
        log.info("RegionDeletedEvent occured : {}", event);
    }
}
