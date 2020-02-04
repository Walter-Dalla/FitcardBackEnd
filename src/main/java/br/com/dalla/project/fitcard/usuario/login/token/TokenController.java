package br.com.dalla.project.fitcard.usuario.login.token;
import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.erros.tipos.ConflictException;
import br.com.dalla.project.fitcard.erros.tipos.ForbiddenException;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class TokenController {


    TokenService tokenService;

    @Autowired
    TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @GetMapping("/user/info")
    public UsuarioModel getUserInfo(HttpServletRequest request, @RequestHeader("Token") String token)
            throws ForbiddenException, BadRequestException, ConflictException {

        return tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
    }

    @PostMapping("/login")
    public TokenModel login(
            HttpServletRequest request, @RequestBody UsuarioModel usuario)
            throws ConflictException, BadRequestException, ForbiddenException {
        TokenModel token =
                tokenService.getToken(usuario, RequestUtils.getIpFromRequest(request));

        return token;
    }

}
