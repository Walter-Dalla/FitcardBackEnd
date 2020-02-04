package br.com.dalla.project.fitcard.usuario.usuario;

import javax.servlet.http.HttpServletRequest;

import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.erros.tipos.ConflictException;
import br.com.dalla.project.fitcard.erros.tipos.UnauthorizedException;
import br.com.dalla.project.fitcard.usuario.login.token.TokenService;
import br.com.dalla.project.fitcard.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

  private UsuarioService UsuarioService;
  private TokenService tokenService;

  @Autowired
  public UsuarioController(UsuarioService usuarioService,
     TokenService tokenService) {
      super();
      UsuarioService = usuarioService;
      this.tokenService = tokenService;
  }


  @GetMapping
  public UsuarioModel getUsuario(HttpServletRequest request, @RequestHeader("Token") String token)
      throws BadRequestException {

    return tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
  }

  @PostMapping("/cadastrar")
  public UsuarioModel cadastrarUsuario(HttpServletRequest request,
          @RequestBody UsuarioModel usuario)
          throws BadRequestException, ConflictException {

    return UsuarioService.cadastrarUsuario(usuario, RequestUtils.getIpFromRequest(request));
  }


  @PatchMapping
  public UsuarioModel alterarUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody UsuarioModel usuarioAlterado)
      throws BadRequestException, UnauthorizedException {

    UsuarioModel usuario = tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));

    return UsuarioService.alterUser(token, usuarioAlterado, usuario);
  }

  @DeleteMapping
  public UsuarioModel desativarUsuario(HttpServletRequest request,
      @RequestHeader("Token") String token) throws BadRequestException {
    return UsuarioService.desativarUsuario(token, RequestUtils.getIpFromRequest(request));
  }
}
