package fr.app.seni.hopital.query.notation_hopital;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetNotationHopitalByIdQuery {
    private final String idNotationHopital;
}
