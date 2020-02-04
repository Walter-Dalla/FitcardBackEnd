package br.com.dalla.project.fitcard.usuario.usuario;

import java.util.Optional;

import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.erros.tipos.ConflictException;
import br.com.dalla.project.fitcard.erros.tipos.UnauthorizedException;
import br.com.dalla.project.fitcard.usuario.login.token.TokenService;
import br.com.dalla.project.fitcard.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

  TokenService tokenService;
  UsuarioRepository usuarioRepository;


  @Autowired
  public UsuarioService(TokenService tokenService, UsuarioRepository usuarioRepository) {
    super();
    this.tokenService = tokenService;
    this.usuarioRepository = usuarioRepository;
  }

  public UsuarioModel getUsuarioById(Integer idUsuario) throws BadRequestException {

    Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(idUsuario);

    if (!usuarioOptional.isPresent())
      throw new BadRequestException("Usuario não encontrado");

    return usuarioOptional.get();
  }

  public UsuarioModel alterUser(String token, UsuarioModel usuarioAlterado, UsuarioModel usuario)
      throws BadRequestException, UnauthorizedException {

    usuario.setEmail(usuarioAlterado.getEmail());
    usuario.setNome(usuarioAlterado.getNome());

    return usuarioRepository.saveAndFlush(usuario);
  }

  public UsuarioModel desativarUsuario(String token, String ip) throws BadRequestException {

    UsuarioModel usuario = tokenService.getUsuarioByToken(token, ip);

    usuario.setAtivo(false);

    return usuarioRepository.saveAndFlush(usuario);
  }

  public UsuarioModel cadastrarUsuario(UsuarioModel usuario, String ipFromRequest)
          throws ConflictException, BadRequestException {

    validacaoAoCriarUsuario(usuario);
    usuario.setAtivo(true);
    return usuarioRepository.save(usuario);
  }

  private void validacaoAoCriarUsuario(UsuarioModel usuario)
          throws ConflictException, BadRequestException {
    if (Utils.isNullOrEmpty(usuario.getNomeLogin())
            || Utils.isNullOrEmpty(usuario.getSenhaLogin()))
      throw new BadRequestException("O usuario deve conter um nome e uma senha");

    if (usuarioRepository.existsByNomeLogin(usuario.getNomeLogin()))
    throw new ConflictException("Nome de usuario já existente");

  }
}
