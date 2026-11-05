package com.estoque.veiculos.dto;

import com.estoque.veiculos.model.StatusVeiculo;
import com.estoque.veiculos.model.Veiculo;

import java.math.BigDecimal;

public record VeiculoResponse(
        Long id,
        Long modeloId,
        String marca,
        String modelo,
        Integer anoFabricacao,
        String cor,
        BigDecimal preco,
        Integer quilometragem,
        StatusVeiculo status) {

    public static VeiculoResponse from(Veiculo v) {
        return new VeiculoResponse(
                v.getId(),
                v.getModelo().getId(),
                v.getModelo().getMarca().getNome(),
                v.getModelo().getNome(),
                v.getAnoFabricacao(),
                v.getCor(),
                v.getPreco(),
                v.getQuilometragem(),
                v.getStatus());
    }
}
