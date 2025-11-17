package br.com.sportlife.service;

import br.com.sportlife.model.Cliente;
import br.com.sportlife.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Cliente salvar(Cliente c) {
        c.setAtivo(true);
        return repo.save(c);
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void desativar(Long id) {
        Cliente c = buscarPorId(id);
        c.setAtivo(false);
        repo.save(c);
    }

    public List<Cliente> listarAtivos() {
        return repo.findByAtivoTrue();
    }
}
