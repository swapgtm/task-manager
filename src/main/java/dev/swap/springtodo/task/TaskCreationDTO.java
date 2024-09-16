package dev.swap.springtodo.task;

import jakarta.validation.constraints.NotBlank;

public record TaskCreationDTO(
        @NotBlank(message = "The task title is Mandatory!")
        String title,
        String description
) {
}
