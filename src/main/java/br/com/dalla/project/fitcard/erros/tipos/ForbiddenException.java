package br.com.dalla.project.fitcard.erros.tipos;

import java.util.List;

public class ForbiddenException extends BaseException {

    private static final long serialVersionUID = -5894500780841176189L;

    public ForbiddenException(String mensagem) {
        super("Bad Request", mensagem, 400);
    }

    public ForbiddenException(List<String> mensagem) {
        super("Bad Request", mensagem, 403);
    }
}
