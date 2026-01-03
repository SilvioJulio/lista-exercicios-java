
package org.academiadb.prova;


import org.academiadb.prova.validacaoSuperMercado.ValidadorItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.academiadb.prova.validacaoSuperMercado.ValidadorPedido.*;

public class Pedido {
    private ArrayList<Item> itens;
    private double valorTotalPedido =  0.0 ;

    public Pedido() {
        this.itens = (ArrayList<Item>) validarPedidoBasico (new ArrayList<>(), "itens");
        this.valorTotalPedido = validarValorTotalPedido (valorTotalPedido, "valorTotalPedido");
    }

    public void calculateValorTotalPedido() {
        double total = 0.0;
        for (Item item : itens) {
            total += item.getPrecoTotal();
        }
        this.valorTotalPedido = total;
    }

    public boolean adicionarItemNaLista(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) return false;
        Item item = new Item(produto, quantidade);
        itens.add(item);
        calculateValorTotalPedido();
        return true;
    }


   public void adicionarItemNaLista(List<Item> novosItens) {
        // valida a lista e cada item
        ValidadorItem.validarLista(novosItens);
        for (Item it : novosItens) {
            ValidadorItem.validarItemBasico(it);
        }

        this.itens.addAll(novosItens);
        calculateValorTotalPedido();
    }


    public void imprimirPedido() {
        System.out.println("==== #Itens do Pedido# ====");
        if (itens.isEmpty()) {
            System.out.println("Sem itens.");
        } else {
            for (Item item : itens) {
                System.out.println("- " + item.getProduto().getNome()
                        + " | Quantidade: " + item.getQuantidade()
                        + " | Preço Total: R$ " + String.format("%.2f", item.getPrecoTotal()));
            }
        }
        System.out.println("Valor Total do Pedido: R$ " + String.format("%.2f", valorTotalPedido));
    }


    public void imprimirValorTotalPedido() {
        System.out.println("Valor Total do Pedido: R$ " + String.format("%.2f", valorTotalPedido));
    }

    public void limparCarrinho() {
        this.itens.clear();
        this.valorTotalPedido = 0.0;
    }

    public ArrayList<Item> getItens() {

        return new ArrayList<>(itens);
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = (itens == null) ? new ArrayList<>() : itens;
        calculateValorTotalPedido();
    }


    public double getValorTotalPedido() { return valorTotalPedido; }


    public double calcularTroco(double valorPago) {
        calculateValorTotalPedido();
        if (valorPago < valorTotalPedido) {
            double falta = valorTotalPedido - valorPago;
            System.out.println("Valor pago insuficiente. Falta: R$ " + String.format("%.2f", falta));
            return -1.0;
        }
        double troco = valorPago - valorTotalPedido;
        System.out.println("Troco: R$ " + String.format("%.2f", troco));
        return troco;
    }


    public Map<String, Integer> calcularMenorQuantidadeDeCedulasEMoedas(double troco) {
        Map<String, Integer> resultado = new LinkedHashMap<>();

        if (troco < 0) {
            System.out.println("Troco inválido (negativo).");
            return resultado;
        }

        int centavos = (int) Math.round(troco * 100);

        int[] notas = {20000, 10000, 5000, 2000, 1000, 500, 200};
        String[] nomesNotas = {"R$ 200", "R$ 100", "R$ 50", "R$ 20", "R$ 10", "R$ 5", "R$ 2"};

        int[] moedas = {100, 50, 25, 10, 5, 1};
        String[] nomesMoedas = {"R$ 1", "R$ 0.50", "R$ 0.25", "R$ 0.10", "R$ 0.05", "R$ 0.01"};

        // Notas
        for (int i = 0; i < notas.length; i++) {
            int quantidade = centavos / notas[i];
            if (quantidade > 0) { resultado.put(nomesNotas[i], quantidade); centavos -= quantidade * notas[i];}}

        for (int i = 0; i < moedas.length; i++) {
            int quantidade = centavos / moedas[i];
            if (quantidade > 0) {
                resultado.put(nomesMoedas[i], quantidade);
                centavos -= quantidade * moedas[i];
            } }return resultado;}

    public void imprimirDistribuicaoTroco(Map<String, Integer> distribuicao) {
        if (distribuicao == null || distribuicao.isEmpty()) {
            System.out.println("Nenhuma cédula/moeda necessária.");return;}

        System.out.println("=== Menor quantidade de cédulas/moedas ===");
        for (Map.Entry<String, Integer> e : distribuicao.entrySet()) { System.out.printf("%s: %d%n", e.getKey(), e.getValue());}}


    public void removerItemPorIndice(int indice) {
        if (indice < 0 || indice >= itens.size()) {
            // índice inválido, não faz nada
            return;}
        itens.remove(indice);calculateValorTotalPedido();}


    public void adicionarItem(Produto produto, int novaQuantidade) {
        if (produto == null || novaQuantidade <= 0) return;

        // Procura se já existe um Item para o produto
        for (Item it : itens) {
            if (it.getProduto().getId() == produto.getId()) {
                // Atualiza a quantidade do item existente
                it.setQuantidade(novaQuantidade);
                // Recalcula o preço total do item (assumindo que Item recalcula internamente)
                calculateValorTotalPedido();return;}}

        // Não existia -> adiciona novo
        itens.add(new Item(produto, novaQuantidade));calculateValorTotalPedido();}

    public void calcularValorTotal() {
        calculateValorTotalPedido();
    }
}

