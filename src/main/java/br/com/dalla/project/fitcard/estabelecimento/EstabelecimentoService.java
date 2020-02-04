
package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;
import br.com.dalla.project.fitcard.categoria.CategoriaRepository;
import br.com.dalla.project.fitcard.categoria.CategoriaUtils;
import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.erros.tipos.BaseException;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.utils.ErroUtils;
import br.com.dalla.project.fitcard.utils.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService implements Serializable {

    EstabelecimentoRepository estabelecimentoRepository;
    CategoriaRepository categoriaRepository;

    @Autowired
    EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository, CategoriaRepository categoriaRepository){
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    List<EstabelicimentoModel> getAtributos(
            String cnpj, String razaoSocial, String nomeFantasia, String email,
            String endereco, String cidade, String estado, String cep,
            String telefone, Date dataCadastro, Date dataAlteracao,
            String categoria, Boolean status, String conta, String agencia, UsuarioModel usuario) {

        CategoriaModel categoriaObj = (CategoriaModel) OptionalUtils.isObjectPresent
                (categoriaRepository.findByCategoria(categoria));

        return estabelecimentoRepository.findAllByEstabelecimento(usuario.getId(), cnpj,  razaoSocial,  nomeFantasia,
                email,  endereco,  cidade,  estado,
                cep, telefone,  dataCadastro,
                dataAlteracao,  categoriaObj,  status,  conta,
                agencia);
    }

    public EstabelicimentoModel addEstabelecimento(EstabelicimentoModel estabelecimento, UsuarioModel usuario)
            throws BaseException {

        List<String> erro = new ArrayList<>();

        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpj(estabelecimento.getCnpj());

        OptionalUtils.objectCanotBePresent(estabelecimentoOptional, erro,
                "Já há um estabelecimento com esse cnpj");

        validaEstabelecimento(estabelecimento, erro);

        ErroUtils.validaErro(erro, new BadRequestException(erro));

        estabelecimento.setUsuario(usuario);

        return  estabelecimentoRepository.save(estabelecimento);
    }


    public EstabelicimentoModel alterEstabelecimento(EstabelicimentoModel estabelicimento, UsuarioModel usuario)
            throws BaseException {



        List<String> erro = new ArrayList<>();
        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpjAndUsuario(estabelicimento.getCnpj(), usuario);

        EstabelicimentoModel estabelecimentoNoBanco = (EstabelicimentoModel)
                OptionalUtils.addErrorIfObjectIsNotPresent(estabelecimentoOptional,
                        new BadRequestException("Não há um estabelecimento com esse cnpj"));

        EstabelecimentoUtils.copyObjectIfNewIsNotNull(estabelicimento, estabelecimentoNoBanco);
        validaEstabelecimento(estabelicimento, erro);
        estabelecimentoNoBanco.setCategoria(estabelicimento.getCategoria());

        ErroUtils.validaErro(erro, new BadRequestException(erro));

        return estabelecimentoRepository.save(estabelecimentoNoBanco);
    }

    public EstabelicimentoModel deleteEstabelecimento(String cnpj, UsuarioModel usuario)
            throws BaseException {

        List<String> erro = new ArrayList<>();
        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpjAndUsuario(cnpj, usuario);


        OptionalUtils.addErrorIfObjectIsNotPresent(estabelecimentoOptional,
                new BadRequestException("Não há um estabelecimento com esse cnpj"));

        ErroUtils.validaErro(erro, new BadRequestException(erro));

        estabelecimentoRepository.deleteByCnpj(cnpj);
        return estabelecimentoOptional.get();
    }

    private void validaEstabelecimento(EstabelicimentoModel estabelecimento, List<String> erro){

        EstabelecimentoUtils.verifyNotNullInformation(estabelecimento, erro);

        CategoriaUtils categoriaUtils = new CategoriaUtils(categoriaRepository);
        categoriaUtils.validaCategoria(estabelecimento, erro);

    }
}
