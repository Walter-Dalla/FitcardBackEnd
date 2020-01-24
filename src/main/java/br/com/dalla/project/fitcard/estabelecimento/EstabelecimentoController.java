package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;
import br.com.dalla.project.fitcard.erros.BadRequestException;
import br.com.dalla.project.fitcard.usuario.login.token.TokenService;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    EstabelecimentoService estabelecimentoService;
    TokenService tokenService;
    @Autowired
    EstabelecimentoController(EstabelecimentoService estabelecimentoService, TokenService tokenService){
        super();
        this.estabelecimentoService = estabelecimentoService;
        this.tokenService = tokenService;
    }

    @GetMapping
    List<EstabelicimentoModel> getAtributos(HttpServletRequest request,
        @RequestParam(required = false) String cnpj,
        @RequestParam(required = false) String razaoSocial,
        @RequestParam(required = false) String nomeFantasia,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String endereco,
        @RequestParam(required = false) String cidade,
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) String cep,
        @RequestParam(required = false) String telefone,
        @RequestParam(required = false) Date dataCadastro,
        @RequestParam(required = false) Date dataAlteracao,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Boolean status,
        @RequestParam(required = false) String conta,
        @RequestParam(required = false) String agencia,
        @RequestHeader("Token") String token) throws BadRequestException{

        UsuarioModel usuario =
                tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));


        return estabelecimentoService.getAtributos(cnpj,  razaoSocial,  nomeFantasia,
                email,  endereco,  cidade,  estado,
                cep,  telefone,  dataCadastro,
                dataAlteracao,  categoria,  status,  conta,
                agencia, usuario);
    }

    @PostMapping
    EstabelicimentoModel addAtributos(HttpServletRequest request,
      @RequestHeader("Token") String token, @RequestBody EstabelicimentoModel estabelicimentoModel) throws BadRequestException {
        UsuarioModel usuario =
                tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
        return estabelecimentoService.addEstabelecimento(estabelicimentoModel, usuario);
    }

    @PatchMapping
    EstabelicimentoModel alterAtributos(HttpServletRequest request,
        @RequestHeader("Token") String token,
        @RequestBody EstabelicimentoModel estabelicimentoModel) throws BadRequestException {
        UsuarioModel usuario =
                tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
        return estabelecimentoService.alterEstabelecimento(estabelicimentoModel, usuario);
    }

    @DeleteMapping
    EstabelicimentoModel deleteAtributos(
            HttpServletRequest request,
            @RequestHeader("Token") String token,
            @RequestParam(value = "cnpj", required = true) String cnpj) throws BadRequestException {
        UsuarioModel usuario =
                tokenService.getUsuarioByToken(token, RequestUtils.getIpFromRequest(request));
        return estabelecimentoService.deleteEstabelecimento(cnpj, usuario);
    }

}
