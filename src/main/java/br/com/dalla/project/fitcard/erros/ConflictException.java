package br.com.dalla.project.fitcard.erros;

import java.util.List;

public class ConflictException extends BaseException{


    private static final long serialVersionUID = -5894500780841176188L;

    public ConflictException(String mensagem) {
        super("Conflict", mensagem, 400);
    }

    public ConflictException(List<String> mensagem) {
        super("Conflict", mensagem, 409);
    }
}
