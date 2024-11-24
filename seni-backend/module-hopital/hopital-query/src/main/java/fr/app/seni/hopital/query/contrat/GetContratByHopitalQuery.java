package fr.app.seni.hopital.query.contrat;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetContratByHopitalQuery {
    private final String idHopital;
}
