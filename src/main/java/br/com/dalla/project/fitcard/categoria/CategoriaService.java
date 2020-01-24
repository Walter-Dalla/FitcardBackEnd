package br.com.dalla.project.fitcard.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    List<CategoriaModel> getCategoria(){

        return categoriaRepository.findAll();
    }
}
