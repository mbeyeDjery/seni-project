package fr.app.seni.core.dto.request;

import fr.app.seni.core.enums.ContratHopitalStatus;

public record ContratStatutRequest(String idContrattHopital, ContratHopitalStatus statut, String motif) {
}
