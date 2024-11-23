package fr.app.seni.type.hopital.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetTypeHopitalByIdQuery {
    private final String idTypeHopital;
}
