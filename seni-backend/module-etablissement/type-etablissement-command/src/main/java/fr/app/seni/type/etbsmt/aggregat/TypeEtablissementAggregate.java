package fr.app.seni.type.etbsmt.aggregat;


import fr.app.seni.core.cqrs.type_etablissement.command.CreateTypeEtablissementCommand;
import fr.app.seni.core.cqrs.type_etablissement.command.DeleteTypeEtablissementCommand;
import fr.app.seni.core.cqrs.type_etablissement.command.UpdateTypeEtablissementCommand;
import fr.app.seni.core.cqrs.type_etablissement.query.TypeEtablissementCreatedEvent;
import fr.app.seni.core.cqrs.type_etablissement.query.TypeEtablissementDeletedEvent;
import fr.app.seni.core.cqrs.type_etablissement.query.TypeEtablissementUpdatedEvent;
import fr.app.seni.core.dto.TypeEtablissementDto;
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
public class TypeEtablissementAggregate {

    @AggregateIdentifier
    private String  idAggregate;
    private TypeEtablissementDto typeEtablissementDto;

    @CommandHandler
    public TypeEtablissementAggregate(CreateTypeEtablissementCommand command){
        log.info("CreateTypeEtablissementCommand : {}", command);
        AggregateLifecycle.apply(
                new TypeEtablissementCreatedEvent(
                        command.getId(),
                        command.getTypeEtablissementDto()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(TypeEtablissementCreatedEvent event){
        log.info("TypeEtablissementCreatedEvent occured : {}", event);
        this.idAggregate = event.getId();
        this.typeEtablissementDto = event.getTypeEtablissementDto();
    }

    @CommandHandler
    public void updateHandler(UpdateTypeEtablissementCommand command){
        log.info("UpdateTypeEtablissementCommand : {}", command);
        AggregateLifecycle.apply(
                new TypeEtablissementUpdatedEvent(
                        command.getId(),
                        command.getTypeEtablissementDto()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(TypeEtablissementUpdatedEvent event){
        log.info("TypeEtablissementUpdatedEvent occured : {}", event);
        this.typeEtablissementDto = event.getTypeEtablissementDto();
    }

    @CommandHandler
    public void deleteHandler(DeleteTypeEtablissementCommand command){
        log.info("DeleteTypeEtablissementCommand : {}", command);
        AggregateLifecycle.apply(
                new TypeEtablissementDeletedEvent(
                        command.getId(),
                        command.getIdTypeEtab()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(TypeEtablissementDeletedEvent event){
        log.info("TypeEtablissementUpdatedEvent occured : {}", event);
    }
}
