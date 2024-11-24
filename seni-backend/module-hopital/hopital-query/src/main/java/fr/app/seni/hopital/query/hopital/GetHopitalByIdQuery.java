package fr.app.seni.hopital.query.hopital;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetHopitalByIdQuery {
    private final String idHopital;
}
