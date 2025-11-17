package br.com.sportlife.repository;

import br.com.sportlife.model.InscricaoTurma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoTurmaRepository extends JpaRepository<InscricaoTurma, Long> {

    long countByTurmaId(Long turmaId);
}
