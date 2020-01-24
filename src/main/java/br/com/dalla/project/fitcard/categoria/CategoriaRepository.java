package br.com.dalla.project.fitcard.categoria;


import br.com.dalla.project.fitcard.estabelecimento.EstabelicimentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer> {

    List<CategoriaModel> findAll();

    Optional<CategoriaModel> findByCategoria(String categoria);
}
