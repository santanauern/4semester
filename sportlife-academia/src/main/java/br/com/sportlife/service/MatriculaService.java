package br.com.sportlife.service;

import br.com.sportlife.model.Cliente;
import br.com.sportlife.model.Matricula;
import br.com.sportlife.model.Plano;
import br.com.sportlife.repository.ClienteRepository;
import br.com.sportlife.repository.MatriculaRepository;
import br.com.sportlife.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaService {

    private final MatriculaRepository repo;
    private final ClienteRepository clienteRepo;
    private final PlanoRepository planoRepo;

    public MatriculaService(MatriculaRepository repo,
                            ClienteRepository clienteRepo,
                            PlanoRepository planoRepo) {
        this.repo = repo;
        this.clienteRepo = clienteRepo;
        this.planoRepo = planoRepo;
    }

    public List<Matricula> listarTodas() {
        return repo.findAll();
    }

    public List<Matricula> listarAtivas() {
        return repo.findByAtivaTrue();
    }

    public Matricula criarMatricula(Long clienteId, Long planoId, LocalDate dataInicio) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Plano plano = planoRepo.findById(planoId)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));

        Matricula m = new Matricula();
        m.setCliente(cliente);
        m.setPlano(plano);
        m.setDataInicio(dataInicio);
        if (plano.getDuracaoMeses() != null) {
            m.setDataFim(dataInicio.plusMonths(plano.getDuracaoMeses()));
        }
        m.setAtiva(true);
        return repo.save(m);
    }

    public void encerrarMatricula(Long id) {
        Matricula m = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada"));
        m.setAtiva(false);
        repo.save(m);
    }
}
