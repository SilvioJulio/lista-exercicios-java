package org.academiadb.prova;

import org.academiadb.prova.validacaoSuperMercado.ValidadorEstoque;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.academiadb.prova.validacaoSuperMercado.ValidadorEstoque.validarProdutoExiste;
import static org.academiadb.prova.validacaoSuperMercado.ValidadorEstoque.validarQuantidadePositiva;


public class Estoque {
    private final AtomicInteger contador = new AtomicInteger(0);
    private final Map<Integer, Produto> porId = new HashMap<>();
    private final Map<String, Integer> idPorNome = new HashMap<>();

    public int gerarId() {
        return contador.incrementAndGet();
    }

    public Produto encontraProdutoPorId(int id) {
        return porId.get(id);
    }

    public Produto encontraProdutoPorNome(String nome) {
        if (nome == null) return null;
        Integer id = idPorNome.get(nome.toLowerCase(Locale.ROOT));
        return id == null ? null : porId.get(id);
    }


    public boolean cadastrarProduto(Produto produto) {
        if (produto == null) return false;

        if (porId.containsKey(produto.getId())) {
            return false;
        }

        porId.put(produto.getId(), produto);
        idPorNome.put(produto.getNome().toLowerCase(Locale.ROOT), produto.getId());
        return true;
    }


    public int cadastrarComIdNovo(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        int id;
        do {
            id = gerarId();
        } while (porId.containsKey(id));
        produto.setId(id);
        porId.put(id, produto);
        idPorNome.put(produto.getNome().toLowerCase(Locale.ROOT), id);
        return id;
    }


    public boolean darBaixaEmEstoque(int produtoId, int quantidadeParaDarBaixa) {
        validarQuantidadePositiva(quantidadeParaDarBaixa, "quantidade para dar baixa");

        Produto produto = porId.get(produtoId);
        validarProdutoExiste(produto);

        int atual = produto.getQuantidadeEmEstoque();
        ValidadorEstoque.validarDisponibilidade(atual, quantidadeParaDarBaixa);

        ValidadorEstoque.validarNaoAbaixoDoMinimo(atual, quantidadeParaDarBaixa, produto.getEstoqueMinimo());

        produto.setQuantidadeEmEstoque(atual - quantidadeParaDarBaixa);
        return true;
    }


    public boolean darBaixaEmEstoquePorNome(String nome, int quantidadeParaDarBaixa) {
        Produto produto = encontraProdutoPorNome(nome);
        validarQuantidadePositiva (quantidadeParaDarBaixa, "quantidade para dar baixa");
        validarProdutoExiste (produto);
        int atual = produto.getQuantidadeEmEstoque();
        if (atual < quantidadeParaDarBaixa) return false;
        produto.setQuantidadeEmEstoque(atual - quantidadeParaDarBaixa);
        return true;
    }


    public boolean disponivelEmEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return true;
        Produto p = porId.get(produto.getId());
        return p == null || p.getQuantidadeEmEstoque() < quantidade;
    }

    public void imprimeCatalogo() {
        System.out.println("===## Catálogo de Produtos ##===");
        if (porId.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            porId.values().forEach(System.out::println);
        }
    }

    public List<Produto> getProdutos() { return new ArrayList<>(porId.values()); }
    public int getId() { return contador.get(); }
}
