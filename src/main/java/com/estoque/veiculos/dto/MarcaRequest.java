package com.estoque.veiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarcaRequest(
        @NotBlank(message = "O nome da marca e obrigatorio")
        @Size(max = 60, message = "O nome da marca pode ter no maximo 60 caracteres")
        String nome) {
}
