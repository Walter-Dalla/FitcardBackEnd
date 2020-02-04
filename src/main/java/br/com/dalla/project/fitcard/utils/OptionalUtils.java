package br.com.dalla.project.fitcard.utils;

import br.com.dalla.project.fitcard.erros.tipos.BaseException;

import java.util.List;
import java.util.Optional;

public class OptionalUtils {

    public static Object isObjectPresent(Object obj) {
        Optional<Object> objOptional = null;

        if(obj instanceof Optional<?>)
            objOptional = ((Optional) obj);

        if(objOptional.isPresent())
            return objOptional.get();
        return null;
    }

    public static void objectCanotBePresent(Optional<?> objOptional, List<String> listaErro, String nomeDoErro){

        if(listaErro != null && objOptional.isPresent())
            listaErro.add(nomeDoErro);
    }

    public static Object addErrorIfObjectIsNotPresent(Optional<?> objOptional, List<String> listaErro, String nomeDoErro){

        if(listaErro != null && !objOptional.isPresent()) {
            listaErro.add(nomeDoErro);
            return null;
        }
        return objOptional.get();
    }

    public static Object addErrorIfObjectIsNotPresent(Optional<?> objOptional, BaseException nomeDoErro) throws BaseException {

        if(nomeDoErro != null && !objOptional.isPresent())
            throw nomeDoErro;
        return objOptional.get();
    }
}
