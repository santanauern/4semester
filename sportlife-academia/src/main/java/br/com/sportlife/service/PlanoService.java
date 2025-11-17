package br.com.sportlife.service;

import br.com.sportlife.model.Plano;
import br.com.sportlife.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    private final PlanoRepository repo;

    public PlanoService(PlanoRepository repo) {
        this.repo = repo;
    }

    public List<Plano> listarTodos() {
        return repo.findAll();
    }

    public Plano salvar(Plano p) {
        p.setAtivo(true);
        return repo.save(p);
    }

    public Plano buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    public void desativar(Long id) {
        Plano p = buscarPorId(id);
        p.setAtivo(false);
        repo.save(p);
    }
}
