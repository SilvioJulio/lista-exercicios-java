package org.academiadb.prova;

import java.util.Locale;

import static org.academiadb.prova.validacaoSuperMercado.ValidadorItem.validarQuantidadeItem;

public class Item {
    private final Produto produto;
    private int quantidade;
    private double precoTotal;

    public Item(Produto produto, int quantidade) {

        this.produto = produto;
        this.quantidade = validarQuantidadeItem(quantidade, "quantidade");
        this.precoTotal = produto.getPreco() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int novaQuantidade) {
        if (novaQuantidade <= 0) {
            return;
        }
        this.quantidade = novaQuantidade;
        this.precoTotal = produto.getPreco() * novaQuantidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Item{produto=%s, qtd=%d, total=R$ %.2f}",
                produto.getNome(), quantidade, precoTotal
        );
    }
}
