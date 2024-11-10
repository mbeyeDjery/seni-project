package fr.app.seni.core.dto;

public record ChangePasswordRequest(String oldPassword, String newPassword) {
}
