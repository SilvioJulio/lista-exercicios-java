package org.academiadb.prova;


import java.util.Locale;

import static org.academiadb.prova.validacaoSuperMercado.ValidadorProduto.*;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;
    private  int estoqueMinimo = 10;

    public Produto(int id, String nome, double preco, int quantidadeEmEstoque) {
        this.id = id;
        this.nome = ValidadorNomeProduto(nome,"nome");
        this.preco = verrifcarPrecoNegativo(preco, "preco");
        this.quantidadeEmEstoque = estoqueNaoDeveSerNegativa(quantidadeEmEstoque, "quantidadeEmEstoque");

    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque; }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Produto{id= %d|nome= '%s'|preco= %.2f| quantidadeEmEstoque= %d}",
                id, nome, preco, quantidadeEmEstoque);
    }


}
