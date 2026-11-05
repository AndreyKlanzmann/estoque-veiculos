package com.estoque.veiculos.dto;

import com.estoque.veiculos.model.StatusVeiculo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VeiculoRequest(
        @NotNull(message = "O modelo e obrigatorio")
        Long modeloId,

        @NotNull(message = "O ano de fabricacao e obrigatorio")
        @Min(value = 1900, message = "Ano de fabricacao invalido")
        @Max(value = 2100, message = "Ano de fabricacao invalido")
        Integer anoFabricacao,

        @NotBlank(message = "A cor e obrigatoria")
        @Size(max = 30, message = "A cor pode ter no maximo 30 caracteres")
        String cor,

        @NotNull(message = "O preco e obrigatorio")
        @Positive(message = "O preco deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "A quilometragem e obrigatoria")
        @PositiveOrZero(message = "A quilometragem nao pode ser negativa")
        Integer quilometragem,

        @NotNull(message = "O status e obrigatorio")
        StatusVeiculo status) {
}
