package com.estoque.veiculos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ModeloRequest(
        @NotBlank(message = "O nome do modelo e obrigatorio")
        @Size(max = 80, message = "O nome do modelo pode ter no maximo 80 caracteres")
        String nome,

        @NotNull(message = "A marca e obrigatoria")
        Long marcaId) {
}
