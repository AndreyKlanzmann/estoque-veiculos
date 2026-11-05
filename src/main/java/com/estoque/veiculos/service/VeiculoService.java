package com.estoque.veiculos.service;

import com.estoque.veiculos.dto.VeiculoRequest;
import com.estoque.veiculos.exception.RecursoNaoEncontradoException;
import com.estoque.veiculos.model.StatusVeiculo;
import com.estoque.veiculos.model.Veiculo;
import com.estoque.veiculos.repository.VeiculoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final ModeloService modeloService;

    public VeiculoService(VeiculoRepository repository, ModeloService modeloService) {
        this.repository = repository;
        this.modeloService = modeloService;
    }

    public Veiculo criar(VeiculoRequest request) {
        Veiculo veiculo = new Veiculo();
        copiarDados(request, veiculo);
        return repository.save(veiculo);
    }

    /**
     * Lista os veiculos aplicando apenas os filtros que foram informados.
     */
    public List<Veiculo> listar(Long marcaId, Long modeloId, BigDecimal precoMin,
                                BigDecimal precoMax, Integer ano, StatusVeiculo status) {

        Specification<Veiculo> filtro = (root, query, cb) -> cb.conjunction();

        if (marcaId != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.equal(root.get("modelo").get("marca").get("id"), marcaId));
        }
        if (modeloId != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.equal(root.get("modelo").get("id"), modeloId));
        }
        if (precoMin != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("preco"), precoMin));
        }
        if (precoMax != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("preco"), precoMax));
        }
        if (ano != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.equal(root.get("anoFabricacao"), ano));
        }
        if (status != null) {
            filtro = filtro.and((root, query, cb) ->
                    cb.equal(root.get("status"), status));
        }

        return repository.findAll(filtro, Sort.by("id"));
    }

    public Veiculo buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Veiculo " + id + " nao encontrado"));
    }

    public Veiculo atualizar(Long id, VeiculoRequest request) {
        Veiculo veiculo = buscar(id);
        copiarDados(request, veiculo);
        return repository.save(veiculo);
    }

    public void remover(Long id) {
        repository.delete(buscar(id));
    }

    private void copiarDados(VeiculoRequest request, Veiculo veiculo) {
        veiculo.setModelo(modeloService.buscar(request.modeloId()));
        veiculo.setAnoFabricacao(request.anoFabricacao());
        veiculo.setCor(request.cor());
        veiculo.setPreco(request.preco());
        veiculo.setQuilometragem(request.quilometragem());
        veiculo.setStatus(request.status());
    }
}
