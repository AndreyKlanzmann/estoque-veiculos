package com.estoque.veiculos.dto;

import com.estoque.veiculos.model.Modelo;

public record ModeloResponse(Long id, String nome, Long marcaId, String marca) {

    public static ModeloResponse from(Modelo modelo) {
        return new ModeloResponse(
                modelo.getId(),
                modelo.getNome(),
                modelo.getMarca().getId(),
                modelo.getMarca().getNome());
    }
}
