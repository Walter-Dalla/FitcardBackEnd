package br.com.dalla.project.fitcard.usuario.login.encrypt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.usuario.login.token.TokenModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;

public class TokenEncriptacao {

  public TokenModel gerarToken(UsuarioModel usuario, String requestIp)
      throws BadRequestException {
   String token;

    token = usuario.getId() +
            TokenConstats.separador
            + usuario.getNome()
            + TokenConstats.separador
            + usuario.getNomeLogin()
            + TokenConstats.separador
            + TokenConstats.nomeDoSistema
            + TokenConstats.separador
            + ZonedDateTime.now(ZoneId.of(TokenConstats.zoneId))
            .format(DateTimeFormatter.ofPattern(TokenConstats.dateFormat))
            + TokenConstats.separador
            + requestIp;

    token = token.trim();

    TokenModel tokenAcesso = new TokenModel();

    tokenAcesso.setUsuario(usuario);

    AES.setKey(TokenConstats.key);

    tokenAcesso.setToken(AES.encrypt(token, TokenConstats.key));
    return tokenAcesso;
  }


  public List<String> decriptarToken(String token) throws BadRequestException {
    String dados = AES.decrypt(token, TokenConstats.key);

    List<String> dadosList = Arrays.asList(dados.split(TokenConstats.separador));
    Integer sizeSplitBySemicolun = dadosList.size();

    if (sizeSplitBySemicolun != TokenConstats.numberOfItensInToken)
      throw new BadRequestException("Token invalido");

    return dadosList;
  }
}
