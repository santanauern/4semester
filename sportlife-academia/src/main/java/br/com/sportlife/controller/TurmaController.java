package br.com.sportlife.controller;

import br.com.sportlife.model.Turma;
import br.com.sportlife.service.TurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin(origins = "*")
public class TurmaController {

    private final TurmaService service;

    public TurmaController(TurmaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Turma> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Turma criar(@RequestBody Turma turma, @RequestParam Long modalidadeId) {
        return service.salvar(turma, modalidadeId);
    }

    @GetMapping("/{id}")
    public Turma buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void desativar(@PathVariable Long id) {
        service.desativar(id);
    }
}
