package org.academiadb.prova;



import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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


    public boolean cadastrarProduto(Produto p) {
        if (p == null) return false;

        if (porId.containsKey(p.getId())) {
            return false;
        }

        porId.put(p.getId(), p);
        idPorNome.put(p.getNome().toLowerCase(Locale.ROOT), p.getId());
        return true;
    }


    public int cadastrarComIdNovo(Produto p) {
        if (p == null) throw new IllegalArgumentException("Produto não pode ser nulo");
        int id;
        do {
            id = gerarId();
        } while (porId.containsKey(id));
        p.setId(id);
        porId.put(id, p);
        idPorNome.put(p.getNome().toLowerCase(Locale.ROOT), id);
        return id;
    }

    public boolean darBaixaEmEstoque(int produtoId, int quantidadeParaDarBaixa) {
        Produto p = porId.get(produtoId);
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
        Produto p = porId.get(produto.getId());
        return (p == null) ? 0 : p.getQuantidadeEmEstoque();
    }

    public boolean temEstoque(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return false;
        Produto p = porId.get(produto.getId());
        return p != null && p.getQuantidadeEmEstoque() >= quantidade;
    }

    public void imprimeCatalogo() {
        System.out.println("=== Catálogo de Produtos ===");
        if (porId.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            porId.values().forEach(System.out::println);
        }
    }

    public List<Produto> getProdutos() { return new ArrayList<>(porId.values()); }
    public int getId() { return contador.get(); }
}
