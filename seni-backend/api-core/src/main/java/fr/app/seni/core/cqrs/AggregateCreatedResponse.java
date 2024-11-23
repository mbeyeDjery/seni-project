package fr.app.seni.core.cqrs;

import lombok.Builder;

@Builder
public record AggregateCreatedResponse(String id, String message) {
}
