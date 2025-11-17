package br.com.sportlife.controller;

import br.com.sportlife.model.Plano;
import br.com.sportlife.service.PlanoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
@CrossOrigin(origins = "*")
public class PlanoController {

    private final PlanoService service;

    public PlanoController(PlanoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Plano> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Plano criar(@RequestBody Plano plano) {
        return service.salvar(plano);
    }

    @GetMapping("/{id}")
    public Plano buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void desativar(@PathVariable Long id) {
        service.desativar(id);
    }
}
