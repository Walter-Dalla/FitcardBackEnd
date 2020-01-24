package br.com.dalla.project.fitcard.usuario.login.token;

import br.com.dalla.project.fitcard.usuario.login.token.TokenModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenModel, Integer> {

    Optional<TokenModel> findByUsuario(UsuarioModel usuario);
}
