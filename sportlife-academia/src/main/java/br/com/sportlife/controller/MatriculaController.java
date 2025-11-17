package br.com.sportlife.controller;

import br.com.sportlife.model.Matricula;
import br.com.sportlife.service.MatriculaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Matricula> listar() {
        return service.listarTodas();
    }

    @GetMapping("/ativas")
    public List<Matricula> listarAtivas() {
        return service.listarAtivas();
    }

    @PostMapping
    public Matricula criar(@RequestParam Long clienteId,
                           @RequestParam Long planoId,
                           @RequestParam String dataInicio) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        return service.criarMatricula(clienteId, planoId, inicio);
    }

    @PostMapping("/{id}/encerrar")
    public void encerrar(@PathVariable Long id) {
        service.encerrarMatricula(id);
    }
}
