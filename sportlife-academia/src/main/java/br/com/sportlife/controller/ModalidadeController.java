package br.com.sportlife.controller;

import br.com.sportlife.model.Modalidade;
import br.com.sportlife.service.ModalidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modalidades")
@CrossOrigin(origins = "*")
public class ModalidadeController {

    private final ModalidadeService service;

    public ModalidadeController(ModalidadeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Modalidade> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Modalidade criar(@RequestBody Modalidade modalidade) {
        return service.salvar(modalidade);
    }

    @GetMapping("/{id}")
    public Modalidade buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void desativar(@PathVariable Long id) {
        service.desativar(id);
    }
}
