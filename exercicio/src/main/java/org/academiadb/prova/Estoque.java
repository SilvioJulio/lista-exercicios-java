package org.academiadb.prova;


import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private int id = 1; // simples
    private final List<Produto> listaDeProdutos = new ArrayList<>();


    public void cadastrarProduto(Produto p) {

        if (p != null) listaDeProdutos.add(p);

    }
    public void gerarId() {
        this.id++;
    }

    public Produto encontraProdutoPorNome(String nome) {
        if (nome == null) return null;
        for (Produto p : listaDeProdutos) {
            if (nome.equalsIgnoreCase(p.getNome())) return p;
        }
        return null;
    }

    public Produto encontraProdutoPorId(int id) {
        for (Produto p : listaDeProdutos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public boolean darBaixaEmEstoque(int produtoId, int quantidadeParaDarBaixa) {
        Produto p = encontraProdutoPorId(produtoId);
        if (p == null || quantidadeParaDarBaixa <= 0) return false;
        int atual = p.getQuantidadeEmEstoque();
        if (atual < quantidadeParaDarBaixa) return false;
        p.setQuantidadeEmEstoque(atual - quantidadeParaDarBaixa);
        return true;
    }

    public boolean darBaixaEmEstoquePorNome(String nome, int quantidadeParaDarBaixa) {
        Produto p = encontraProdutoPorNome(nome);
        if (p == null || quantidadeParaDarBaixa <= 0) return false;
        int atual = p.getQuantidadeEmEstoque();
        if (atual < quantidadeParaDarBaixa) return false;
        p.setQuantidadeEmEstoque(atual - quantidadeParaDarBaixa);
        return true;
    }

    public int getQuantidadeAtualEmEstoque(Produto produto) {
        if (produto == null) return 0;
        Produto p = encontraProdutoPorId(produto.getId());
        return (p == null) ? 0 : p.getQuantidadeEmEstoque();
    }

    public boolean temEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return false;
        Produto p = encontraProdutoPorId(produto.getId());
        return p != null && p.getQuantidadeEmEstoque() >= quantidade;
    }

    public void imprimeCatalogo() {
        System.out.println("=== Catálogo de Produtos ===");
        if (listaDeProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : listaDeProdutos) {
                System.out.println(p);
            }
        }
    }

    public List<Produto> getProdutos() { return new ArrayList<>(listaDeProdutos); }
    public int getId() { return id; }
}
