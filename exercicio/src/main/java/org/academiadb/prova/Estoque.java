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

    // ===== Getter necessário para o Menu =====
    public Map<String, Integer> getIdPorNome() {
        return idPorNome;
    }

    private String normalizarNome(String nome) {
        if (nome == null) return null;

        return nome.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }

    public int gerarId() {
        return contador.incrementAndGet();
    }

    public Produto encontraProdutoPorId(int id) {
        return porId.get(id);
    }

    public Produto encontraProdutoPorNome(String nome) {
        if (nome == null) return null;
        String chave = normalizarNome(nome);
        Integer id = idPorNome.get(chave);
        return id == null ? null : porId.get(id);
    }

    // Opcional: facilita checar duplicidade no Menu
    public boolean nomeJaExiste(String nome) {
        String chave = normalizarNome(nome);
        return chave != null && idPorNome.containsKey(chave);
    }

    public boolean cadastrarProduto(Produto produto) {
        if (produto == null) return false;

        if (porId.containsKey(produto.getId())) {
            return false;
        }

        // Garante que o índice de nome usa a mesma normalização
        String chave = normalizarNome(produto.getNome());
        if (idPorNome.containsKey(chave)) {
            return false;
        }

        porId.put(produto.getId(), produto);
        idPorNome.put(chave, produto.getId());
        return true;
    }

    public int cadastrarComIdNovo(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto não pode ser nulo");

        int id;
        do {
            id = gerarId();
        } while (porId.containsKey(id));

        String chave = normalizarNome(produto.getNome());
        if (idPorNome.containsKey(chave)) {
            throw new IllegalArgumentException("Nome já existe no estoque.");
        }

        produto.setId(id);
        porId.put(id, produto);
        idPorNome.put(chave, id);
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
        validarQuantidadePositiva(quantidadeParaDarBaixa, "quantidade para dar baixa");
        validarProdutoExiste(produto);

        int atual = produto.getQuantidadeEmEstoque();
        if (atual < quantidadeParaDarBaixa) return false;

        int novo = atual - quantidadeParaDarBaixa;


        // se você também quer validar estoque mínimo por nome:
        if (novo < produto.getEstoqueMinimo()) {
            return false;
        }

        produto.setQuantidadeEmEstoque(novo);
        return true;
    }

    public boolean temEstoque(Produto produto, int quantidade) {
        return produto != null && quantidade > 0 && produto.getQuantidadeEmEstoque() >= quantidade;
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


    public boolean disponivelEmEstoque(Produto produto, int quantidade) {
        return temEstoque(produto, quantidade);
    }

}

