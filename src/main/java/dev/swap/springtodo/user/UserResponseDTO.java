package dev.swap.springtodo.user;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
