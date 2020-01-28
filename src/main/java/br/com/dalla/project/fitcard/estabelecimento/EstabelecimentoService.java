
package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;
import br.com.dalla.project.fitcard.categoria.CategoriaRepository;
import br.com.dalla.project.fitcard.erros.BadRequestException;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;
import br.com.dalla.project.fitcard.utils.Utils;
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

        CategoriaModel categoriaObj = null;
        Optional<CategoriaModel> categoriaOptional = categoriaRepository.findByCategoria(categoria);
        if(categoriaOptional.isPresent())
            categoriaObj = categoriaOptional.get();

        return estabelecimentoRepository.findAllByEstabelecimento(usuario.getId(), cnpj,  razaoSocial,  nomeFantasia,
                email,  endereco,  cidade,  estado,
                cep, telefone,  dataCadastro,
                dataAlteracao,  categoriaObj,  status,  conta,
                agencia);
    }

    public EstabelicimentoModel addEstabelecimento(EstabelicimentoModel estabelecimento, UsuarioModel usuario)
            throws BadRequestException {

        List<String> erro = new ArrayList<>();
        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpj(estabelecimento.getCnpj());

        if(estabelecimentoOptional.isPresent())
            erro.add("Já há um estabelecimento com esse cnpj");


        EstabelecimentoUtils.validEstabelecimentoOnAdd(estabelecimento, erro);


        if(estabelecimento.getCategoria() != null){
            Optional<CategoriaModel> categoriaOptional =
                    categoriaRepository.findByCategoria(estabelecimento.getCategoria().getCategoria());
            if(!categoriaOptional.isPresent())
                erro.add("Essa categoria não existe");
            else
                erro = EstabelecimentoUtils.validaCategoria(estabelecimento, categoriaOptional.get(), erro);
        }

        if(!erro.isEmpty())
            throw new BadRequestException(erro);

        estabelecimento.setUsuario(usuario);

        EstabelicimentoModel estabelicimentoSaved = estabelecimentoRepository.save(estabelecimento);

        return  estabelicimentoSaved;
    }


    public EstabelicimentoModel alterEstabelecimento(EstabelicimentoModel estabelicimento, UsuarioModel usuario)
            throws BadRequestException {

        List<String> erro = new ArrayList<>();
        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpjAndUsuario(estabelicimento.getCnpj(), usuario);

        if(!estabelecimentoOptional.isPresent())
            erro.add("Não há um estabelecimento com esse cnpj");

        EstabelicimentoModel estabelecimentoNoBanco = estabelecimentoOptional.get();

        EstabelecimentoUtils.validaEstabelecimentoOnEdditing(estabelicimento, estabelecimentoNoBanco);

        if(estabelicimento.getCategoria() != null) {
            Optional<CategoriaModel> categoriaOptional =
                    categoriaRepository.findByCategoria(estabelicimento.getCategoria().getCategoria());
            if(!categoriaOptional.isPresent())
                erro.add("Categoria não encontrada!");
            else{
                estabelecimentoNoBanco.setCategoria(categoriaOptional.get());
        	erro = EstabelecimentoUtils.validaCategoria(estabelicimento, categoriaOptional.get(), erro);
		}
	}

        if(!erro.isEmpty())
            throw new BadRequestException(erro);

        return estabelecimentoRepository.save(estabelecimentoNoBanco);
    }

    public EstabelicimentoModel deleteEstabelecimento(String cnpj, UsuarioModel usuario)
            throws BadRequestException {

        List<String> erro = new ArrayList<>();
        Optional<EstabelicimentoModel> estabelecimentoOptional =
                estabelecimentoRepository.findByCnpjAndUsuario(cnpj, usuario);

        if(!estabelecimentoOptional.isPresent())
            erro.add("Não há um estabelecimento com esse cnpj");

        if(!erro.isEmpty())
            throw new BadRequestException(erro);

        estabelecimentoRepository.deleteByCnpj(cnpj);
        return estabelecimentoOptional.get();
    }
}
