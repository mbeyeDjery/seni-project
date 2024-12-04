package fr.app.seni.core.dto;

import fr.app.seni.core.enums.HopitalStatus;

public record HopitalStatutRequest(String idHopital, HopitalStatus statut, String motif) {
}
