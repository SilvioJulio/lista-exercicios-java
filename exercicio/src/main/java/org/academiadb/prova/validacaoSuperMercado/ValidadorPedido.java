package org.academiadb.prova.validacaoSuperMercado;



import org.academiadb.prova.Item;

import java.util.List;

public final class ValidadorPedido {
    private ValidadorPedido() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static List<Item> validarPedidoBasico(List<Item> itens, String campo) {
        if (itens == null) {
            throw new IllegalArgumentException("O campo: " + campo + " não pode ser nulo.");
        }

        return itens;
    }

    public  static Double validarValorTotalPedido(Double valorTotalPedido, String campo) {
        if (valorTotalPedido == null || valorTotalPedido < 0) {
            throw new IllegalArgumentException("O campo: " + campo + " não pode ser nulo ou negativo.");
        }

        return valorTotalPedido;
    }


}


