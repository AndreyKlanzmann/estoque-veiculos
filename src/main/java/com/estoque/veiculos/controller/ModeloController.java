package com.estoque.veiculos.controller;

import com.estoque.veiculos.dto.ModeloRequest;
import com.estoque.veiculos.dto.ModeloResponse;
import com.estoque.veiculos.model.Modelo;
import com.estoque.veiculos.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ModeloResponse> criar(@Valid @RequestBody ModeloRequest request) {
        Modelo modelo = service.criar(request);
        return ResponseEntity.created(URI.create("/modelos/" + modelo.getId()))
                .body(ModeloResponse.from(modelo));
    }

    @GetMapping
    public List<ModeloResponse> listar(@RequestParam(required = false) Long marcaId) {
        return service.listar(marcaId).stream().map(ModeloResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ModeloResponse buscar(@PathVariable Long id) {
        return ModeloResponse.from(service.buscar(id));
    }

    @PutMapping("/{id}")
    public ModeloResponse atualizar(@PathVariable Long id, @Valid @RequestBody ModeloRequest request) {
        return ModeloResponse.from(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
