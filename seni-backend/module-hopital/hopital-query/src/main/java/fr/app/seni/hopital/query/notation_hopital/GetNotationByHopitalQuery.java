package fr.app.seni.hopital.query.notation_hopital;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetNotationByHopitalQuery {
    private final String idHopital;
}
