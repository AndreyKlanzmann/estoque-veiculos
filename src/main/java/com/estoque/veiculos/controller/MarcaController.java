package com.estoque.veiculos.controller;

import com.estoque.veiculos.dto.MarcaRequest;
import com.estoque.veiculos.dto.MarcaResponse;
import com.estoque.veiculos.model.Marca;
import com.estoque.veiculos.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService service;

    public MarcaController(MarcaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MarcaResponse> criar(@Valid @RequestBody MarcaRequest request) {
        Marca marca = service.criar(request);
        return ResponseEntity.created(URI.create("/marcas/" + marca.getId()))
                .body(MarcaResponse.from(marca));
    }

    @GetMapping
    public List<MarcaResponse> listar() {
        return service.listar().stream().map(MarcaResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MarcaResponse buscar(@PathVariable Long id) {
        return MarcaResponse.from(service.buscar(id));
    }

    @PutMapping("/{id}")
    public MarcaResponse atualizar(@PathVariable Long id, @Valid @RequestBody MarcaRequest request) {
        return MarcaResponse.from(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
