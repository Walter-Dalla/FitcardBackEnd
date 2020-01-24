package br.com.dalla.project.fitcard.usuario.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    Optional<UsuarioModel> findByNomeLoginAndSenhaLogin(String nomeLogin, String senhaLogin);

    boolean existsByNomeLogin(String nomeLogin);
}
