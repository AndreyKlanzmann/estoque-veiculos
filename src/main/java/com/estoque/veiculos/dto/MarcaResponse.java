package com.estoque.veiculos.dto;

import com.estoque.veiculos.model.Marca;

public record MarcaResponse(Long id, String nome) {

    public static MarcaResponse from(Marca marca) {
        return new MarcaResponse(marca.getId(), marca.getNome());
    }
}
