package fr.app.seni.hopital.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetHopitalByIdQuery {
    private final String idHopital;
}
