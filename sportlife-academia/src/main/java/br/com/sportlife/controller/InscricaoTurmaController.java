package br.com.sportlife.controller;

import br.com.sportlife.model.InscricaoTurma;
import br.com.sportlife.service.InscricaoTurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin(origins = "*")
public class InscricaoTurmaController {

    private final InscricaoTurmaService service;

    public InscricaoTurmaController(InscricaoTurmaService service) {
        this.service = service;
    }

    @GetMapping
    public List<InscricaoTurma> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public InscricaoTurma inscrever(@RequestParam Long turmaId,
                                    @RequestParam Long clienteId) {
        return service.inscrever(turmaId, clienteId);
    }
}
