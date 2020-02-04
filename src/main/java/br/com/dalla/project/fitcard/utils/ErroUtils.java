package br.com.dalla.project.fitcard.utils;

import br.com.dalla.project.fitcard.erros.tipos.BadRequestException;
import br.com.dalla.project.fitcard.erros.tipos.BaseException;

import java.util.List;

public class ErroUtils {

    public static void validaErro(List<String> listaErros, BaseException httpErro)
            throws BaseException {
        if(!listaErros.isEmpty())
            throw httpErro;
    }
}
