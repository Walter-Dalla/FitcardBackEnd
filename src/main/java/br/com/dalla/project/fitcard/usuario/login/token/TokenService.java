package br.com.dalla.project.fitcard.usuario.login.token;

import br.com.dalla.project.fitcard.erros.BadRequestException;
import br.com.dalla.project.fitcard.erros.ForbiddenException;

import br.com.dalla.project.fitcard.usuario.login.token.utils.TokenUtils;
import br.com.dalla.project.fitcard.usuario.login.encrypt.TokenEncriptacao;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    private TokenEncriptacao tokenEncriptacao = new TokenEncriptacao();
    @Autowired
    private UsuarioRepository usuarioRepository;

    public TokenModel getToken(UsuarioModel usuarioRequisicao, String requestIp)
            throws ForbiddenException, BadRequestException {

        UsuarioModel usuario = getUsuarioByLogin(usuarioRequisicao.getNomeLogin(),
                usuarioRequisicao.getSenhaLogin());

        TokenUtils.validaStatusUsuario(usuario);

        deletaTokenAtivoNoBanco(usuario);

        return tokenRepository.save(tokenEncriptacao.gerarToken(usuario, requestIp));

    }

    public void deletaTokenAtivoNoBanco(UsuarioModel usuario) {

        Optional<TokenModel> tokenDeAcessoOptional =
                tokenRepository.findByUsuario(usuario);

        if (tokenDeAcessoOptional.isPresent())
            tokenRepository.deleteById(tokenDeAcessoOptional.get().getId());

    }


    UsuarioModel getUsuarioByLogin(String nome, String senha) throws BadRequestException {
        Optional<UsuarioModel> usuarioOptional =
                usuarioRepository.findByNomeLoginAndSenhaLogin(nome, senha);
        if(!usuarioOptional.isPresent())
            throw new BadRequestException("Usuario não encontrado");
        return usuarioOptional.get();
    }

    private UsuarioModel getUsuarioById(Integer id) throws BadRequestException {
        Optional<UsuarioModel> usuarioModelOptional =
                usuarioRepository.findById(id);
        if(!usuarioModelOptional.isPresent())
            throw new BadRequestException("Token invalido");
        return usuarioModelOptional.get();
    }

    public UsuarioModel getUsuarioByToken(String token, String ip) throws BadRequestException {

        List<String> dadosList = tokenEncriptacao.decriptarToken(token);

        UsuarioModel usuario = getUsuarioById(Integer.parseInt(dadosList.get(0)));

        TokenUtils.validaToken(usuario, dadosList, ip);

        return usuario;
    }
}
