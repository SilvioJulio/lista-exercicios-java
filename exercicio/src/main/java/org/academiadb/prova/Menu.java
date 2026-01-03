package org.academiadb.prova;

import java.util.*;

public class Menu {
    private final Estoque estoque;
    private final Pedido pedido;

    public Menu(Estoque estoque, Pedido pedido) {
        this.estoque = estoque;
        this.pedido = pedido;
    }

    public void controlaMenu() {
        try (Scanner input = new Scanner(System.in)) {
            int opcao;
            do {
                imprimirCabecalho();
                opcao = numeroInteirovalido(input, "Escolha a opção: ");
                switchCase(opcao, input);
            } while (opcao != 0);
        }
    }

    private void switchCase(int opcao, Scanner input) {
        switch (opcao) {
            case 1 -> mostrarEstoque();
            case 2 -> cadastrarProduto(input);
            case 3 -> buscarProduto(input);
            case 4 -> reporEstoque(input);
            case 5 -> darBaixaEmEstoque(input);
            case 6 -> adicionarItemAoPedido(input);
            case 7 -> alterarQuantidadeItemPedido(input);
            case 8 -> removerItemDoPedido(input);
            case 9 -> verPedido();
            case 10 -> finalizarPedido(input);
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void mostrarEstoque() {
        estoque.imprimeCatalogo();
    }


    private void cadastrarProduto(Scanner input) {
        estoque.gerarId();
        int id = estoque.getId();

        String nomeEntrada = lerLinha(input, "Nome do produto: ");
        String chave = nomeEntrada.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);

        // Validação simples de duplicidade
        if (estoque.getIdPorNome().containsKey(chave)) {
            System.out.println("Já existe no estoque. Escolha outro nome.");
            return;
        }

        double preco = numeroDoubleValido(input, "Preço (R$): ");
        int quantidade = numeroInteirovalido(input, "Quantidade inicial em estoque: ");

        // Normaliza nome para exibição
        String nomeNormalizado = nomeEntrada.trim().replaceAll("\\s+", " ");

        Produto produto = new Produto(id, nomeNormalizado, preco, quantidade);
        estoque.cadastrarProduto(produto);

        // Atualiza índice de nomes
        estoque.getIdPorNome().put(chave, id);

        System.out.println("Produto cadastrado com sucesso. ID: " + id);
    }


    private void buscarProduto(Scanner input) {
        System.out.println("\nBuscar produto por:");
        System.out.println("[1] = ID");
        System.out.println("[2] = Nome");
        int opcao = numeroInteirovalido(input, "Escolha: ");

        Produto produto;
        if (opcao == 1) {
            int id = numeroInteirovalido(input, "ID: ");
            produto = estoque.encontraProdutoPorId(id);
        } else if (opcao == 2) {
            String nome = lerLinha(input, "Nome: ");
            produto = estoque.encontraProdutoPorNome(nome);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println(produto == null ? "Produto não encontrado." : produto);
    }

    private void reporEstoque(Scanner input) {
        int id = numeroInteirovalido(input, "ID do produto: ");
        int quantidade = numeroInteirovalido(input, "Quantidade para repor: ");

        Produto produto = estoque.encontraProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }
        System.out.println("===================================================");
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + quantidade);
        System.out.printf("Estoque de %s atualizado para %d.%n",
                produto.getNome(), produto.getQuantidadeEmEstoque());
        System.out.println("===================================================");
    }

    private void darBaixaEmEstoque(Scanner input) {
        System.out.println("\nDar baixa por:");
        System.out.println("[1] ID");
        System.out.println("[2] Nome");
        int opcao = numeroInteirovalido(input, "Escolha: ");
        int quantidade = numeroInteirovalido(input, "Quantidade para dar baixa: ");

        boolean verificaIdNome;
        if (opcao == 1) {
            int id = numeroInteirovalido(input, "ID do produto: ");
            verificaIdNome = estoque.darBaixaEmEstoque(id, quantidade);
        } else if (opcao == 2) {
            String nome = lerLinha(input, "Nome do produto: ");
            verificaIdNome = estoque.darBaixaEmEstoquePorNome(nome, quantidade);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        System.out.println(verificaIdNome ? "Baixa realizada com sucesso."
                : "Não foi possível realizar a baixa (produto/quantidade inválidos).");
    }


    private void adicionarItemAoPedido(Scanner input) {
        int id = numeroInteirovalido(input, "ID do produto: ");
        Produto produto = estoque.encontraProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        int quantidade = numeroInteirovalido(input, "Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }


        if (!estoque.temEstoque(produto, quantidade)) {
            System.out.println("Estoque insuficiente.");
            return;
        }

        pedido.adicionarItem(produto, quantidade);
        System.out.println("Item adicionado/atualizado no pedido.");
    }


    private void alterarQuantidadeItemPedido(Scanner input) {

        List<Item> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }


        System.out.println("=== Itens do Pedido ===");
        for (int i = 0; i < itens.size(); i++) {
            Item it = itens.get(i);
            System.out.printf("%d) %s | Quantidade atual: %d | Total: R$ %.2f%n",
                    i + 1,
                    it.getProduto().getNome(),
                    it.getQuantidade(),
                    it.getPrecoTotal());
        }


        int indice;
        while (true) {
            String promptSelecao = (itens.size() == 1)
                    ? "Selecione o item (digite 1): "
                    : "Digite o número do item que deseja alterar (1 a " + itens.size() + "): ";
            int entrada = numeroInteirovalido(input, promptSelecao);
            indice = entrada - 1;
            if (indice >= 0 && indice < itens.size()) break;
            System.out.println("Número inválido. Digite um valor entre 1 e " + itens.size() + ".");
        }

        Item item = itens.get(indice);
        int quantidadeAtual = item.getQuantidade();
        Produto produto = item.getProduto();

        int novaQuantidade;
        while (true) {
            novaQuantidade = numeroInteirovalido(input,
                    "Quantidade atual: " + quantidadeAtual +
                            ". Digite nova quantidade pode ser maior ou menor que a atual: ");
            if (novaQuantidade > 0) break;
            System.out.println("Quantidade inválida. Digite um número quantidade pode ser maior ou menor que a atual.");
        }

        // Verificar estoque
        if (estoque.disponivelEmEstoque(produto, novaQuantidade)) {
            System.out.println("Estoque insuficiente para a quantidade informada.");
            return;
        }

        item.setQuantidade(novaQuantidade);

        pedido.calcularValorTotal();
        System.out.printf("Quantidade atualizada: %s agora tem %d unidades.%n",
                produto.getNome(), novaQuantidade);
    }

    private void removerItemDoPedido(Scanner input) {
        verPedido();
        List<Item> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        // Exibir itens numerados para facilitar a escolha
        System.out.println("=== Itens do Pedido ===");
        for (int i = 0; i < itens.size(); i++) {
            Item it = itens.get(i);
            System.out.printf("%d) %s | Quantidade: %d | Total: R$ %.2f%n",
                    i + 1,
                    it.getProduto().getNome(),
                    it.getQuantidade(),
                    it.getPrecoTotal());
        }

        // Escolha com validação
        int indice;
        while (true) {
            int entrada = numeroInteirovalido(input,
                    "Digite o número do item que deseja remover (1 a " + itens.size() + "): ");
            indice = entrada - 1;
            if (indice >= 0 && indice < itens.size()) break;
            System.out.println("Número inválido. Digite um valor entre 1 e " + itens.size() + ".");
        }

        pedido.removerItemPorIndice(indice);
        pedido.calcularValorTotal();
        System.out.println("Item removido.");
    }

    private void verPedido() {
        pedido.calcularValorTotal();
        pedido.imprimirPedido();
    }



    private void finalizarPedido(Scanner input) {
        List<Item> itens = pedido.getItens();
        if (itens == null || itens.isEmpty()) {
            System.out.println("Pedido vazio.");
            return;
        }

        // Verifica estoque (quantidade suficiente para cada item)
        boolean temEmEstoque = true;
        for (Item i : itens) {
            if (estoque.disponivelEmEstoque(i.getProduto(), i.getQuantidade())) {
                temEmEstoque = false;
                System.out.printf("Falha: estoque insuficiente para %s%n", i.getProduto().getNome());
            }
        }
        if (!temEmEstoque) {
            System.out.println("Não foi possível finalizar. Ajuste as quantidades.");
            return;
        }

        // Pagamento + troco
        pedido.calcularValorTotal();
        double total = pedido.getValorTotalPedido();
        System.out.println("Total a pagar: R$ " + String.format("%.2f", total));

        double valorPago = numeroDoubleValido(input, "Valor pago (R$): ");
        double troco = pedido.calcularTroco(valorPago);
        if (troco < 0) {
            System.out.println("Valor pago insuficiente. Faltam R$ " + String.format("%.2f", -troco));
            return;
        }

        // Exibir distribuição do troco em notas/moedas
        Map<String, Integer> dist = pedido.calcularMenorQuantidadeDeCedulasEMoedas(troco);
        pedido.imprimirDistribuicaoTroco(dist);


        String recibo = gerarReciboTexto(itens, total, valorPago, troco);
        imprimirRecibo(recibo);

        // Dar baixa no estoque
        List<String> avisos = new ArrayList<>();
        List<String> itensComFalhaNaBaixa = new ArrayList<>();
        boolean todasBaixasAplicadas = true;

        for (Item i : itens) {
            Produto produto = i.getProduto();
            int produtoId = produto.getId();
            int quantidade = i.getQuantidade();
            try {
                boolean ok = estoque.darBaixaEmEstoque(produtoId, quantidade);
                if (!ok) {
                    todasBaixasAplicadas = false;
                    itensComFalhaNaBaixa.add(produto.getNome());
                    avisos.add("Não foi possível dar baixa no produto '" + produto.getNome() + "'.");
                }
            } catch (IllegalArgumentException e) {
                // Ex.: quantidade <= 0, produto não encontrado (regra de entrada)
                todasBaixasAplicadas = false;
                itensComFalhaNaBaixa.add(produto.getNome());
                avisos.add("Erro no item '" + produto.getNome() + "': " + e.getMessage());
            } catch (IllegalStateException e) {
                // Ex.: insuficiente ou abaixo do mínimo — usar a mensagem amigável que você formata no validador
                todasBaixasAplicadas = false;
                itensComFalhaNaBaixa.add(produto.getNome());
                avisos.add(e.getMessage());
            }
        }

        if (!avisos.isEmpty()) {
            System.out.println();
            System.out.println("============ Aviso importante ============");
            for (String msg : avisos) {
                System.out.println(msg);
            }
        }

        if (todasBaixasAplicadas) {
            System.out.println("Pedido finalizado e estoque atualizado.");
            pedido.limparCarrinho();
        } else {
            System.out.println("Não foi possível realzar operação !!!.");
            pedido.limparCarrinho();
        }
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

    // Inteiro válido (loop até ser válido)
    private int numeroInteirovalido(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = input.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private double numeroDoubleValido(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = input.nextLine().trim();

            if (linha.isEmpty()) {
                System.out.println("Entrada vazia. Digite um valor (ex.: 12,34).");
                continue;
            }

            linha = linha.replace("R$", "").trim();

            if (linha.contains(",")) {
                linha = linha.replace(".", "");
            }
            linha = linha.replace(",", ".");

            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido (ex.: 12,34).");
            }
        }
    }

    private String lerLinha(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private String gerarReciboTexto(List<Item> itens, double total, double valorPago, double troco) {
        StringBuilder recibo = new StringBuilder(
                "================ RECIBO ================\n" +
                        String.format("%-20s %5s %10s%n", "Produto", "Qtd", "Total") +
                        "----------------------------------------------\n"
        );

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

        recibo.append("----------------------------------------------\n")
                .append(String.format("TOTAL: R$ %.2f%n", total))
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


