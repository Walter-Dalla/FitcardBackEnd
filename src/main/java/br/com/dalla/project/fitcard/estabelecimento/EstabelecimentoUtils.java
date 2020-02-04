package br.com.dalla.project.fitcard.estabelecimento;

import br.com.dalla.project.fitcard.categoria.CategoriaModel;

import java.util.List;

public class EstabelecimentoUtils {

    public static List<String> verifyNotNullInformation(EstabelicimentoModel estabelicimento, List<String> erro){

        if(estabelicimento.getRazaoSocial() == null || estabelicimento.getRazaoSocial().trim() == "")
            erro.add("Rasão social deve ser declarada");

        if(estabelicimento.getCnpj() == null || estabelicimento.getCnpj().length() != 18)
            erro.add("Cnpj deve ser declarado corretamente");

        return erro;
    }


    public static EstabelicimentoModel copyObjectIfNewIsNotNull(
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

        if(estabelicimento.getTelefone() != null )
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
