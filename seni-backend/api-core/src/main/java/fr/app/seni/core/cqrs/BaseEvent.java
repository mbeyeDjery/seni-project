package fr.app.seni.core.cqrs;

import lombok.Getter;

@Getter
public abstract class BaseEvent <T>{
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}