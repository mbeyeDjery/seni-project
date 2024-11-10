package fr.app.seni.core.cqrs;


import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Getter
public abstract class BaseCommand <T>{
    @TargetAggregateIdentifier
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}