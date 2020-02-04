package br.com.dalla.project.fitcard.categoria;

import br.com.dalla.project.fitcard.estabelecimento.EstabelecimentoUtils;
import br.com.dalla.project.fitcard.estabelecimento.EstabelicimentoModel;
import br.com.dalla.project.fitcard.utils.OptionalUtils;

import java.util.List;
import java.util.Optional;

public class CategoriaUtils {

    private CategoriaRepository categoriaRepository;

    public CategoriaUtils( CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public void validaCategoria(EstabelicimentoModel estabelicimento, List<String> erro) {

        Optional<CategoriaModel> categoriaOptional =
                categoriaRepository.findByCategoria(estabelicimento.getCategoria().getCategoria());

        CategoriaModel categoria = (CategoriaModel) OptionalUtils.addErrorIfObjectIsNotPresent(categoriaOptional, erro,
                "Essa categoria não existe");

        if (categoria != null
                && categoria.getCategoria().equals("Supermercado")
                && estabelicimento.getTelefone() == null
                || estabelicimento.getTelefone().equals("")) {
            erro.add("A categoria \"Supermercado\" exige que se adicione um telefone");
        }
        estabelicimento.setCategoria(categoria);
    }

}
