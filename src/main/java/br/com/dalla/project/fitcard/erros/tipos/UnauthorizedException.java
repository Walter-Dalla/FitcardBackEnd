package br.com.dalla.project.fitcard.erros.tipos;

import java.util.List;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String mensagem) {
        super("Unauthorized", mensagem, 401);
    }

    public UnauthorizedException(List<String> mensagem) {
        super("Unauthorized", mensagem, 401);
    }
}
