package br.com.sportlife.service;

import br.com.sportlife.model.Modalidade;
import br.com.sportlife.model.Turma;
import br.com.sportlife.repository.ModalidadeRepository;
import br.com.sportlife.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository repo;
    private final ModalidadeRepository modalidadeRepo;

    public TurmaService(TurmaRepository repo, ModalidadeRepository modalidadeRepo) {
        this.repo = repo;
        this.modalidadeRepo = modalidadeRepo;
    }

    public List<Turma> listarTodas() {
        return repo.findAll();
    }

    public Turma salvar(Turma t, Long modalidadeId) {
        Modalidade modalidade = modalidadeRepo.findById(modalidadeId)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));
        t.setModalidade(modalidade);
        t.setAtiva(true);
        return repo.save(t);
    }

    public Turma buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public void desativar(Long id) {
        Turma t = buscarPorId(id);
        t.setAtiva(false);
        repo.save(t);
    }
}
