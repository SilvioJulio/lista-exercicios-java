package org.academiadb.prova.validacaoSuperMercado;



import org.academiadb.prova.Item;
import org.academiadb.prova.Pedido;

import java.util.List;

public final class ValidadorPedido {
    private ValidadorPedido() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static void validarPedidoBasico(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido nulo.");
        }
        List<Item> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens.");
        }
    }



}


