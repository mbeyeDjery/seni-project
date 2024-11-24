package fr.app.seni.parametre.aggregat;


import fr.app.seni.core.cqrs.secteur.command.CreateSecteurCommand;
import fr.app.seni.core.cqrs.secteur.command.DeleteSecteurCommand;
import fr.app.seni.core.cqrs.secteur.command.UpdateSecteurCommand;
import fr.app.seni.core.cqrs.secteur.event.SecteurCreatedEvent;
import fr.app.seni.core.cqrs.secteur.event.SecteurDeletedEvent;
import fr.app.seni.core.cqrs.secteur.event.SecteurUpdatedEvent;
import fr.app.seni.core.dto.SecteurDto;
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
public class SecteurAggregate {

    @AggregateIdentifier
    private String  id;
    private SecteurDto secteur;

    @CommandHandler
    public SecteurAggregate(CreateSecteurCommand command){
        log.info("CreateVilleCommand : {}", command.getSecteur());
        AggregateLifecycle.apply(new SecteurCreatedEvent(
                        command.getId(),
                        command.getSecteur()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(SecteurCreatedEvent event){
        log.info("SecteurCreatedEvent occured : {}", event.getSecteur());
        this.id = event.getId();
        this.secteur = event.getSecteur();
    }

    @CommandHandler
    public void updateHandler(UpdateSecteurCommand command){
        log.info("UpdateSecteurCommand : {}", command.getSecteur());
        AggregateLifecycle.apply(new SecteurUpdatedEvent(
                        command.getId(),
                        command.getSecteur()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(SecteurUpdatedEvent event){
        log.info("SecteurUpdatedEvent occured : {}", event.getSecteur());
        this.secteur = event.getSecteur();
    }

    @CommandHandler
    public void deleteHandler(DeleteSecteurCommand command){
        log.info("DeleteSecteurCommand : {}", command.getId());
        AggregateLifecycle.apply(new SecteurDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(SecteurDeletedEvent event){
        log.info("SecteurDeletedEvent occured : {}", event);
    }
}
