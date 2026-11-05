package com.estoque.veiculos.service;

import com.estoque.veiculos.dto.ModeloRequest;
import com.estoque.veiculos.exception.RecursoNaoEncontradoException;
import com.estoque.veiculos.model.Marca;
import com.estoque.veiculos.model.Modelo;
import com.estoque.veiculos.repository.ModeloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository repository;
    private final MarcaService marcaService;

    public ModeloService(ModeloRepository repository, MarcaService marcaService) {
        this.repository = repository;
        this.marcaService = marcaService;
    }

    public Modelo criar(ModeloRequest request) {
        Marca marca = marcaService.buscar(request.marcaId());
        return repository.save(new Modelo(request.nome(), marca));
    }

    public List<Modelo> listar(Long marcaId) {
        if (marcaId != null) {
            return repository.findByMarcaId(marcaId);
        }
        return repository.findAll();
    }

    public Modelo buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Modelo " + id + " nao encontrado"));
    }

    public Modelo atualizar(Long id, ModeloRequest request) {
        Modelo modelo = buscar(id);
        modelo.setNome(request.nome());
        modelo.setMarca(marcaService.buscar(request.marcaId()));
        return repository.save(modelo);
    }

    @Transactional
    public void remover(Long id) {
        Modelo modelo = buscar(id);
        repository.delete(modelo);
        repository.flush();
    }
}
