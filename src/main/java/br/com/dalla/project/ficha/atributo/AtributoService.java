package br.com.dalla.project.ficha.atributo;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class AtributoService implements Serializable {


    AtributosModel getAtributos(){
        AtributosModel atributosModel = new AtributosModel();
        atributosModel.id = 0;
        atributosModel.nomeAtributo = "dig dig e";
        System.out.println("eia eia eia");
        return atributosModel;
    }

}
