package br.com.sportlife.controller;

import br.com.sportlife.model.Cliente;
import br.com.sportlife.model.InscricaoTurma;
import br.com.sportlife.service.ClienteService;
import br.com.sportlife.service.InscricaoTurmaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {

    private final ClienteService clienteService;
    private final InscricaoTurmaService inscricaoService;

    public RelatorioController(ClienteService clienteService,
                               InscricaoTurmaService inscricaoService) {
        this.clienteService = clienteService;
        this.inscricaoService = inscricaoService;
    }

    @GetMapping("/clientes-ativos")
    public List<Cliente> clientesAtivos() {
        return clienteService.listarAtivos();
    }

    @GetMapping("/inscricoes")
    public List<InscricaoTurma> inscricoes() {
        return inscricaoService.listarTodas();
    }
}
