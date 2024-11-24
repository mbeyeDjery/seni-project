package fr.app.seni.hopital.query.contrat;


import fr.app.seni.core.enums.ContratHopitalStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetContratByHopitalAndStatusQuery {
    private final String idHopital;
    private final ContratHopitalStatus statut;
}
