package br.com.sportlife.service;

import br.com.sportlife.model.Cliente;
import br.com.sportlife.model.InscricaoTurma;
import br.com.sportlife.model.Turma;
import br.com.sportlife.repository.ClienteRepository;
import br.com.sportlife.repository.InscricaoTurmaRepository;
import br.com.sportlife.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoTurmaService {

    private final InscricaoTurmaRepository repo;
    private final TurmaRepository turmaRepo;
    private final ClienteRepository clienteRepo;

    public InscricaoTurmaService(InscricaoTurmaRepository repo,
                                 TurmaRepository turmaRepo,
                                 ClienteRepository clienteRepo) {
        this.repo = repo;
        this.turmaRepo = turmaRepo;
        this.clienteRepo = clienteRepo;
    }

    public List<InscricaoTurma> listarTodas() {
        return repo.findAll();
    }

    public InscricaoTurma inscrever(Long turmaId, Long clienteId) {
        Turma turma = turmaRepo.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        long inscritos = repo.countByTurmaId(turmaId);
        if (inscritos >= turma.getCapacidade()) {
            throw new RuntimeException("Turma lotada");
        }

        InscricaoTurma insc = new InscricaoTurma();
        insc.setTurma(turma);
        insc.setCliente(cliente);
        return repo.save(insc);
    }
}
