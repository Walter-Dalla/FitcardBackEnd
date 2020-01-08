package br.com.dalla.project.ficha.atributo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atributos")

public class atributoController {

    AtributoService atributoService;

    @Autowired
    atributoController(AtributoService atributoService){
        super();
        this.atributoService = atributoService;
    }

    @GetMapping
    AtributosModel getAtributos(){

        return atributoService.getAtributos();
    }

}
