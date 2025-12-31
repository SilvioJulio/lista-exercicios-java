package org.academiadb.prova.validacaoSuperMercado;


import org.academiadb.prova.Item;

import java.util.List;

public class ValidadorItem {

    public static int validarQuantidadeItem(int quantidade, String campo) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("O campo: " + campo + " deve ser maior que zero.");
        }
        return quantidade;
    }

    public static void validarLista(List<Item> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula ou vazia.");
        }
        int index = 0;
        for (Item it : itens) {
            if (it == null) {
                throw new IllegalArgumentException("Item na posição " + index + " é nulo.");
            }
            index++;
        }
    }

    public static void validarItemBasico(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        if (item.getProduto() == null) {
            throw new IllegalArgumentException("Item deve possuir um produto.");
        }
        validarQuantidadeItem(item.getQuantidade(), "quantidade");

    }
}
