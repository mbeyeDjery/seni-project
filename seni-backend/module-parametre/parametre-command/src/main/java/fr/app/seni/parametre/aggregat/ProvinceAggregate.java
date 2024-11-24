package fr.app.seni.parametre.aggregat;


import fr.app.seni.core.cqrs.province.command.CreateProvinceCommand;
import fr.app.seni.core.cqrs.province.command.DeleteProvinceCommand;
import fr.app.seni.core.cqrs.province.command.UpdateProvinceCommand;
import fr.app.seni.core.cqrs.province.event.ProvinceCreatedEvent;
import fr.app.seni.core.cqrs.province.event.ProvinceDeletedEvent;
import fr.app.seni.core.cqrs.province.event.ProvinceUpdatedEvent;
import fr.app.seni.core.dto.ProvinceDto;
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
public class ProvinceAggregate {

    @AggregateIdentifier
    private String  id;
    private ProvinceDto province;

    @CommandHandler
    public ProvinceAggregate(CreateProvinceCommand command){
        log.info("CreateProvinceCommand : {}", command);
        AggregateLifecycle.apply(new ProvinceCreatedEvent(
                        command.getId(),
                        command.getProvince()
                ));
    }

    @EventSourcingHandler
    public void onCreatedEvent(ProvinceCreatedEvent event){
        log.info("ProvinceCreatedEvent occured : {}", event.getProvince());
        this.id = event.getId();
        this.province = event.getProvince();
    }

    @CommandHandler
    public void updateHandler(UpdateProvinceCommand command){
        log.info("UpdateProvinceCommand : {}", command.getProvince());
        AggregateLifecycle.apply(new ProvinceUpdatedEvent(
                        command.getId(),
                        command.getProvince()
                ));
    }

    @EventSourcingHandler
    public void onUpdatedEvent(ProvinceUpdatedEvent event){
        log.info("ProvinceUpdatedEvent occured : {}", event.getProvince());
        this.province = event.getProvince();
    }

    @CommandHandler
    public void deleteHandler(DeleteProvinceCommand command){
        log.info("DeleteProvinceCommand : {}", command.getId());
        AggregateLifecycle.apply(new ProvinceDeletedEvent(
                        command.getId()
                ));
    }

    @EventSourcingHandler
    public void onDeletedEvent(ProvinceDeletedEvent event){
        log.info("ProvinceDeletedEvent occured : {}", event.getId());
    }
}
