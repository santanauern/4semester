package br.com.sportlife.service;

import br.com.sportlife.model.Modalidade;
import br.com.sportlife.repository.ModalidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalidadeService {

    private final ModalidadeRepository repo;

    public ModalidadeService(ModalidadeRepository repo) {
        this.repo = repo;
    }

    public List<Modalidade> listarTodas() {
        return repo.findAll();
    }

    public Modalidade salvar(Modalidade m) {
        m.setAtiva(true);
        return repo.save(m);
    }

    public Modalidade buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));
    }

    public void desativar(Long id) {
        Modalidade m = buscarPorId(id);
        m.setAtiva(false);
        repo.save(m);
    }
}
