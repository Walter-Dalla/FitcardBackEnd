package br.com.dalla.project.fitcard.usuario.login.token.utils;

import br.com.dalla.project.fitcard.erros.BadRequestException;
import br.com.dalla.project.fitcard.erros.ForbiddenException;
import br.com.dalla.project.fitcard.usuario.login.encrypt.TokenConstats;
import br.com.dalla.project.fitcard.usuario.usuario.UsuarioModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenUtils {

    public static void validaStatusUsuario(UsuarioModel usuario) throws ForbiddenException {
        if (!usuario.isAtivo()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            throw new ForbiddenException("Este usuario está inativo des do dia: "
                    + dateFormat.format(usuario.getDataUltimaAtualizacao()));
        }
    }

    public static void validaToken(UsuarioModel usuario, List<String> dadosList, String ipReq)
            throws BadRequestException {

        List<String> erros = new ArrayList<String>();

        if (dadosList.isEmpty())
            erros.add("Erro: codigo do erro 1");

        if (!dadosList.contains(usuario.getNomeLogin()))
            erros.add("Erro: codigo do erro 2");

        if (!dadosList.contains(TokenConstats.nomeDoSistema))
            erros.add("Erro: codigo do erro 3");

        if (!validaIp(dadosList, ipReq))
            erros.add("Erro: codigo do erro 4, Ip foi alterado, é nessesario relogar no sistema");

        if (!erros.isEmpty()) {
            erros.add(0, "Token sem conexção ou expirado");
            throw new BadRequestException(erros);
        }
    }

    private static boolean validaIp(List<String> dadosList, String ipReq) {

        // Na AWS ele concatena o IP do usuario com o Ip interno deles e separa por ','
        // e isto estava causando mal funcionamento no codigo.
        List<String> ips = Arrays.asList(dadosList.get(dadosList.size() - 1).split(","));
        Boolean hasIp = false;

        for (String ip : Arrays.asList(ipReq.split(","))) {
            if (ips.contains(ip))
                hasIp = true;
        }

        return hasIp;
    }
}
