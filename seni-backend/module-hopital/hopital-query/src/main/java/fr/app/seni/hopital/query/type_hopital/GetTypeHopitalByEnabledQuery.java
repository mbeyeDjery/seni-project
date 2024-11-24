package fr.app.seni.hopital.query.type_hopital;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetTypeHopitalByEnabledQuery {
    private final Boolean enabled;
}
