package com.estoque.veiculos.service;

import com.estoque.veiculos.dto.MarcaRequest;
import com.estoque.veiculos.exception.RecursoNaoEncontradoException;
import com.estoque.veiculos.model.Marca;
import com.estoque.veiculos.repository.MarcaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository repository;

    public MarcaService(MarcaRepository repository) {
        this.repository = repository;
    }

    public Marca criar(MarcaRequest request) {
        return repository.save(new Marca(request.nome()));
    }

    public List<Marca> listar() {
        return repository.findAll();
    }

    public Marca buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Marca " + id + " nao encontrada"));
    }

    public Marca atualizar(Long id, MarcaRequest request) {
        Marca marca = buscar(id);
        marca.setNome(request.nome());
        return repository.save(marca);
    }

    @Transactional
    public void remover(Long id) {
        Marca marca = buscar(id);
        repository.delete(marca);
        repository.flush();
    }
}
