package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;

import java.util.List;

public class EstabelecimentoUtils {

    public static List<String> validEstabelecimentoOnAdd(EstabelicimentoModel estabelicimento, List<String> erro){

        if(estabelicimento.getRazaoSocial() == null || estabelicimento.getRazaoSocial().trim() == "")
            erro.add("Rasão social deve ser declarada");

        if(estabelicimento.getCnpj() == null || estabelicimento.getCnpj().length() != 18)
            erro.add("Cnpj deve ser declarado corretamente");

        return erro;
    }

    public static List<String> validaCategoria(EstabelicimentoModel estabelicimento, CategoriaModel categoria, List<String> erro){

        estabelicimento.setCategoria(categoria);

        if (categoria.getCategoria().equals("Supermercado")) {
            if (estabelicimento.getTelefone() == null || estabelicimento.getTelefone().equals(""))
                erro.add("A categoria \"Supermercado\" exige que se adicione um telefone");

        }

        return erro;
    }


    public static EstabelicimentoModel validaEstabelecimentoOnEdditing(
            EstabelicimentoModel estabelicimento, EstabelicimentoModel estabelecimentoNoBanco){

        if(estabelicimento.getRazaoSocial() != null)
            estabelecimentoNoBanco.setRazaoSocial(estabelicimento.getRazaoSocial());

        if(estabelicimento.getCep() != null)
            estabelecimentoNoBanco.setCep(estabelicimento.getCep());

        if(estabelicimento.getNomeFantasia() != null)
            estabelecimentoNoBanco.setNomeFantasia(estabelicimento.getNomeFantasia());

        if(estabelicimento.getEmail() != null)
            estabelecimentoNoBanco.setEmail(estabelicimento.getEmail());

        if(estabelicimento.getEndereco() != null)
            estabelecimentoNoBanco.setEndereco(estabelicimento.getEndereco());

        if(estabelicimento.getCidade() != null)
            estabelecimentoNoBanco.setCidade(estabelicimento.getCidade());

        if(estabelicimento.getEstado() != null)
            estabelecimentoNoBanco.setEstado(estabelicimento.getEstado());

        if(estabelicimento.getTelefone() != null)
            estabelecimentoNoBanco.setTelefone(estabelicimento.getTelefone());

        if(estabelicimento.getStatus() != null)
            estabelecimentoNoBanco.setStatus(estabelicimento.getStatus());

        if(estabelicimento.getAgencia() != null)
            estabelecimentoNoBanco.setAgencia(estabelicimento.getAgencia());

        if(estabelicimento.getConta() != null)
            estabelecimentoNoBanco.setConta(estabelicimento.getConta());

        return estabelecimentoNoBanco;
    }

}
