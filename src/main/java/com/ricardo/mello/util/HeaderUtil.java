package com.ricardo.mello.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Classe utilitária para criação de cabeçalhos HTTP.
 */
public class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    public static HttpHeaders criarMensagem(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-tribosApp-alert", message);
        headers.add("X-tribosApp-params", param);
        return headers;
    }

    public static HttpHeaders criarMensagemEntidadeCriada(String entidade, String param) {
        return criarMensagem(entidade + " criado(a) com o ID " + param, param);
    }

    public static HttpHeaders criarMensagemEntidadeAtualizada(String entidade, String param) {
        return criarMensagem(entidade + " atualizado(a) com o ID " + param, param);
    }

    public static HttpHeaders criarMensagemEntidadeRemovida(String entidade, String param) {
        return criarMensagem(entidade + " removida com o ID " + param, param);
    }

    public static HttpHeaders criarMensagemFalha(String entidade, String errorKey, String defaultMessage) {
        log.error("Falha ao criar a entidade, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-tribosApp-error", defaultMessage);
        headers.add("X-tribosApp-params", entidade);
        return headers;
    }
}
