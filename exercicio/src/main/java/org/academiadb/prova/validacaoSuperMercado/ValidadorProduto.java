package org.academiadb.prova.validacaoSuperMercado;

public class ValidadorProduto {

    public static String ValidadorNomeProduto(String produto, String campo) {

        if (produto == null || produto.trim().isEmpty() ) {
            throw new IllegalArgumentException("O campo: " + campo + " não pode ser nulo ou vazio.");
        }

        String nome = produto.trim();

        // Aceita apenas letras (Unicode) e espaços
        String regex = "^[\\p{L}\\s]+$";
        if (!nome.matches(regex)) {
            throw new IllegalArgumentException("O campo: " + campo + " deve conter letras");
        }

        if (nome.length() < 2) {
            throw new IllegalArgumentException("O campo: " + campo + " deve ter pelo menos 2 caracteres.");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("O campo: " + campo + " deve ter no máximo 100 caracteres.");
        }

        return nome.replaceAll("\\s+", " ");
    }

    public static Double verrifcarPrecoNegativo(Double preco, String campo) {
        if (preco == null || preco < 0) {
            throw new IllegalArgumentException("O campo: " + campo + " não pode ser nulo ou negativo.");
        }
        return preco;

    }

    public static int estoqueNaoDeveSerNegativa(int quantidade, String campo) {
        if (quantidade <=0) {
            throw new IllegalArgumentException("O campo: " + campo + " não pode ser negativo.");
        }
        return quantidade;
    }


}
