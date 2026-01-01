package org.academiadb.prova;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Menu {
    private final Estoque estoque;
    private final Pedido pedido;

    public Menu(Estoque estoque, Pedido pedido) {
        this.estoque = estoque;
        this.pedido = pedido; }

    public void controlaMenu() {
        try (Scanner sc = new Scanner(System.in)) {
            int opcao;
            do {
                imprimirCabecalho();
                opcao = numeroInteirovalido(sc, "Escolha a opção: ");
                switchCase(opcao, sc);
            } while (opcao != 0);
        }
    }

    private void switchCase(int opcao, Scanner tc) {
        switch (opcao) {
            case 1 -> mostrarEstoque();
            case 2 -> cadastrarProduto(tc);
            case 3 -> buscarProduto(tc);
            case 4 -> reporEstoque(tc);
            case 5 -> darBaixaEmEstoque(tc);
            case 6 -> adicionarItemAoPedido(tc);
            case 7 -> alterarQuantidadeItemPedido(tc);
            case 8 -> removerItemDoPedido(tc);
            case 9 -> verPedido();
            case 10 -> finalizarPedido(tc); // pagamento + troco + recibo + baixa
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void mostrarEstoque() {
        estoque.imprimeCatalogo();}

    private void cadastrarProduto(Scanner sc) {
        estoque.gerarId();
        int id = estoque.getId();

        if (estoque.encontraProdutoPorId(id) != null) {
            System.out.println("ID do produto já cadastrado. Gerando novo ID...");
            // tenta até encontrar outro id
            do {
                estoque.gerarId();
                id = estoque.getId();
            } while (estoque.encontraProdutoPorId(id) != null);
        }

        String nome = lerLinha(sc, "Nome do produto: ");
        double preco = numeroDoubleValido(sc, "Preço (R$): ");
        int quantidade = numeroInteirovalido(sc, "Quantidade inicial em estoque: ");

        Produto p = new Produto(id, nome, preco, quantidade);
        estoque.cadastrarProduto(p);

        System.out.println("Produto cadastrado com sucesso. ID: " + id);
    }



    private void buscarProduto(Scanner sc) {
        System.out.println("\nBuscar produto por:");
        System.out.println("[1] = ID");
        System.out.println("[2] = Nome");
        int op = numeroInteirovalido(sc, "Escolha: ");

        Produto p;
        if (op == 1) {
            int id = numeroInteirovalido(sc, "ID: ");
            p = estoque.encontraProdutoPorId(id);
        } else if (op == 2) {
            String nome = lerLinha(sc, "Nome: ");
            p = estoque.encontraProdutoPorNome(nome);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println(p == null ? "Produto não encontrado." : p);
    }

    private void reporEstoque(Scanner sc) {
        int id = numeroInteirovalido(sc, "ID do produto: ");
        int qtd = numeroInteirovalido(sc, "Quantidade para repor: ");

        Produto p = estoque.encontraProdutoPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }
        System.out.println("===================================================");
        p.setQuantidadeEmEstoque(p.getQuantidadeEmEstoque() + qtd);
        System.out.printf("Estoque de %s atualizado para %d.%n",
                p.getNome(), p.getQuantidadeEmEstoque());
        System.out.println("===================================================");

    }

    private void darBaixaEmEstoque(Scanner sc) {
        System.out.println("\nDar baixa por:");
        System.out.println("1) ID");
        System.out.println("2) Nome");
        int op = numeroInteirovalido(sc, "Escolha: ");
        int qtd = numeroInteirovalido(sc, "Quantidade para dar baixa: ");

        boolean ok ;
        if (op == 1) {
            int id = numeroInteirovalido(sc, "ID do produto: ");
            ok = estoque.darBaixaEmEstoque(id, qtd);
        } else if (op == 2) {
            String nome = lerLinha(sc, "Nome do produto: ");
            ok = estoque.darBaixaEmEstoquePorNome(nome, qtd);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println(ok ? "Baixa realizada com sucesso."
                : "Não foi possível realizar a baixa (produto/quantidade inválidos).");
    }


    private void adicionarItemAoPedido(Scanner sc) {
        int id = numeroInteirovalido(sc, "ID do produto: ");
        Produto p = estoque.encontraProdutoPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int qtd = numeroInteirovalido(sc, "Quantidade: ");
        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }
        if (!estoque.temEstoque(p, qtd)) {
            System.out.println("Estoque insuficiente.");
            return;
        }

        pedido.adicionarItem(p, qtd);
        System.out.println("Item adicionado/atualizado no pedido.");
    }

    private void alterarQuantidadeItemPedido(Scanner sc) {
        verPedido();
        List<Item> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        int indice = numeroInteirovalido(sc, "Informe o índice do item (1..n): ") - 1;
        if (indice < 0 || indice >= itens.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Item item = itens.get(indice);
        int novaQtd = numeroInteirovalido(sc, "Nova quantidade: ");
        if (novaQtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }
        if (!estoque.temEstoque(item.getProduto(), novaQtd)) {
            System.out.println("Estoque insuficiente para a nova quantidade.");
            return;
        }

        pedido.adicionarItem(item.getProduto(), novaQtd);
        pedido.calcularValorTotal();
        System.out.println("Quantidade atualizada.");
    }

    private void removerItemDoPedido(Scanner sc) {
        verPedido();
        List<Item> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        int indice = numeroInteirovalido(sc, "Informe o índice do item (1..n): ") - 1;
        pedido.removerItemPorIndice(indice);
        System.out.println("Item removido (se existir).");
    }

    private void verPedido() {
        pedido.calcularValorTotal();
        pedido.imprimirPedido();
    }

    // ================== FINALIZAÇÃO (pagamento + troco + recibo + baixa) ==================

    private void finalizarPedido(Scanner sc) {
        List<Item> itens = pedido.getItens();
        if (itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        // Verifica estoque
        boolean ok = true;
        for (Item i : itens) {
            if (!estoque.temEstoque(i.getProduto(), i.getQuantidade())) {
                ok = false;
                System.out.printf("Falha: estoque insuficiente para %s%n", i.getProduto().getNome());
            }
        }
        if (!ok) {
            System.out.println("Não foi possível finalizar. Ajuste as quantidades.");
            return;
        }

        // Pagamento + troco
        pedido.calcularValorTotal();
        double total = pedido.getValorTotalPedido();
        System.out.println("Total a pagar: R$ " + String.format("%.2f", total));

        double valorPago = numeroDoubleValido(sc, "Valor pago (R$): ");
        double troco = pedido.calcularTroco(valorPago);
        if (troco < 0) {
            // Valor insuficiente: não finaliza
            return;
        }

        // (Opcional) exibir distribuição do troco em notas/moedas
        Map<String, Integer> dist = pedido.calcularMenorQuantidadeDeCedulasEMoedas(troco);
        pedido.imprimirDistribuicaoTroco(dist);

        // ===== Emite RECIBO (inline, sem classe externa) =====
        String recibo = gerarReciboTexto(itens, total, valorPago, troco);
        imprimirRecibo(recibo);

        // Dá baixa no estoque e conclui
        for (Item i : itens) {
            estoque.darBaixaEmEstoque(i.getProduto().getId(), i.getQuantidade());
        }
        System.out.println("Pedido finalizado e estoque atualizado.");
        pedido.limparCarrinho();
    }


    private void imprimirCabecalho() {
        System.out.println("\n========= SuperMercado =========");
        System.out.println("1) Listar estoque");
        System.out.println("2) Cadastrar produto");
        System.out.println("3) Buscar produto (ID/Nome)");
        System.out.println("4) Repor estoque");
        System.out.println("5) Dar baixa em estoque (ID/Nome)");
        System.out.println("6) Adicionar item ao pedido");
        System.out.println("7) Alterar quantidade de item do pedido");
        System.out.println("8) Remover item do pedido");
        System.out.println("9) Ver pedido");
        System.out.println("10) Finalizar pedido (troco, emissão de recibo e baixa)");
        System.out.println("0) Sair");
    }

    // Só vai dar loop até o usuário digitar um inteiro válido
    private int numeroInteirovalido(Scanner tc, String prompt) {
        System.out.print(prompt);
        while (!tc.hasNextInt()) {
            System.out.print("Digite um número inteiro válido: ");
            tc.next();
        }
        return tc.nextInt();
    }

    // Só vai dar loop até o usuário digitar um double válido
    private double numeroDoubleValido(Scanner tc, String prompt) {
        System.out.print(prompt);
        while (!tc.hasNextDouble()) {
            System.out.print("Digite um número válido (ex.: 12.34): ");
            tc.next();
        }
        return tc.nextDouble();
    }

    private String lerLinha(Scanner sc, String prompt) {
        System.out.print(prompt);
        sc.nextLine(); // consome eventual \n pendente
        return sc.nextLine();
    }


    private String gerarReciboTexto(List<Item> itens, double total, double valorPago, double troco) {

            StringBuilder recibo = new StringBuilder

                    ("================ RECIBO ================\n" +
                    String.format("%-20s %5s %10s%n", "Produto", "Qtd", "Total") +
                    "----------------------------------------------\n");

            if (itens == null || itens.isEmpty()) {
                recibo.append("** Sem itens **\n");
            } else {
                for (Item i : itens) {
                    recibo.append(String.format("%-20s %5d %10.2f%n",
                            i.getProduto().getNome(),
                            i.getQuantidade(),
                            i.getPrecoTotal()));
                }
            }
            recibo.append("----------------------------------------------\n").append
                    (String.format("TOTAL: R$ %.2f%n", total))
                    .append(String.format("PAGO : R$ %.2f%n", valorPago))
                    .append(String.format("TROCO: R$ %.2f%n", troco))
                    .append("==============================================\n");

            return recibo.toString();
        }


        private void imprimirRecibo(String reciboTexto) {
        System.out.println(reciboTexto);
    }

    static void main() {
        Estoque estoque = new Estoque();
        Pedido pedido = new Pedido();
        Menu menu = new Menu(estoque, pedido);
        menu.controlaMenu();
    }
}
