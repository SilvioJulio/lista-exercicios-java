package org.academiadb.prova.validacaoSuperMercado;


import org.academiadb.prova.Produto;

public final class ValidadorEstoque {

    private ValidadorEstoque() {} // utilitária

    public static void validarProdutoExiste(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
    }

    public static void validarQuantidadePositiva(int quantidade, String campo) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A " + campo + " deve ser maior que zero.");
        }
    }


    public static void validarDisponibilidade(int atual, int baixa) {
        if (atual < baixa) {
            throw new IllegalStateException(
                    "Estoque insuficiente: atual=" + atual + ", baixa=" + baixa
            );
        }
    }


    public static void validarNaoAbaixoDoMinimo(int atual, int baixa, int minimo) {
        int novo = atual - baixa;
        if (novo < minimo) {
            throw new IllegalStateException(
                    "Estoque (" + novo + ") abaixo do mínimo (" + minimo + "), deve selecionar quantidade maior ou igual 10, para finlizar a venda."

            );
        }

    }

}
