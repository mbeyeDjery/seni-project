package fr.app.seni.hopital.query.contrat;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetContratByIdQuery {
    private final String idContrattHopital;
}
