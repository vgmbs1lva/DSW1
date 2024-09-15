package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vagas")
public class VagaRestController {

    @Autowired
    private VagaService vagaService;

    // Retorna a lista de todas as vagas em aberto
    @GetMapping
    public ResponseEntity<List<Vaga>> listarVagas() {
        List<Vaga> vagas = vagaService.buscarTodas();
        return new ResponseEntity<>(vagas, HttpStatus.OK);
    }
    
    // Retorna uma vaga específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVaga(@PathVariable Long id) {
        Optional<Vaga> vaga = vagaService.buscarPorId(id);
        if (vaga.isPresent()) {
            return new ResponseEntity<>(vaga.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retorna a lista de vagas de uma empresa específica por ID
    @GetMapping("/empresas/{empresaId}")
    public ResponseEntity<List<Vaga>> listarVagasPorEmpresa(@PathVariable Empresa empresaId) {
        List<Vaga> vagasAbertas = vagaService.buscarVagasAbertasPorEmpresa(empresaId);
        return new ResponseEntity<>(vagasAbertas, HttpStatus.OK);
    }


    // Retorna a lista de vagas em aberto de uma cidade
    @GetMapping("/cidades/{cidade}")
    public ResponseEntity<List<Vaga>> listarVagasPorCidade(@PathVariable String cidade) {
        List<Vaga> vagas = vagaService.buscarPorCidade(cidade);
        return new ResponseEntity<>(vagas, HttpStatus.OK);
    }

    // Cria uma nova vaga
    @PostMapping
    public ResponseEntity<Vaga> criarVaga(@RequestBody Vaga vaga) {
        vagaService.salvar(vaga);
        return new ResponseEntity<>(vaga, HttpStatus.CREATED);
    }

    // Atualiza uma vaga por ID
    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vagaAtualizada) {
        Optional<Vaga> vagaOptional = vagaService.buscarPorId(id);

        if (vagaOptional.isPresent()) {
            Vaga vagaExistente = vagaOptional.get();
            vagaExistente.setDescricao(vagaAtualizada.getDescricao());
            vagaExistente.setRemuneracao(vagaAtualizada.getRemuneracao());
            vagaExistente.setDataLimiteInscricao(vagaAtualizada.getDataLimiteInscricao());
            vagaExistente.setCidade(vagaAtualizada.getCidade());
            vagaExistente.setEmpresa(vagaAtualizada.getEmpresa());

            vagaService.salvar(vagaExistente);
            return new ResponseEntity<>(vagaExistente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Remove uma vaga por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        Optional<Vaga> vagaOptional = vagaService.buscarPorId(id);

        if (vagaOptional.isPresent()) {
            vagaService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
