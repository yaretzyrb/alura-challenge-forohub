package com.yaretzyram.alura.forohub.domains.models.user;

public record UserUpdateDTO(
        Long id,
        String name,
        String email,
        String password
) {
}
