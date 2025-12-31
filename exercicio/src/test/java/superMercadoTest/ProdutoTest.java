package superMercadoTest;

import org.academiadb.prova.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ProdutoTest {

    @Test
    public void testarValidacaoProduto() {
        Produto produto = new Produto(23, "Arroz", 10.0, 5);
        assertAll(
                () -> assertEquals(23, produto.getId()),
                () -> assertEquals("Arroz", produto.getNome()),
                () -> assertEquals(10.0, produto.getPreco()),
                () -> assertEquals(5, produto.getQuantidadeEmEstoque())
        );


    }


    @Test
    void testarToStringProduto() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);

        String esperado = "Produto{id=1, nome='Feijão', preco=8.50, estoque=10}";
        assertEquals(esperado, produto.toString());

    }

    @Test
    void testarSettersProduto() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);

        produto.setId(2);
        produto.setNome("Arroz");
        produto.setPreco(7.25);
        produto.setQuantidadeEmEstoque(20);

        assertAll(
                () -> assertEquals(2, produto.getId()),
                () -> assertEquals("Arroz", produto.getNome()),
                () -> assertEquals(7.25, produto.getPreco()),
                () -> assertEquals(20, produto.getQuantidadeEmEstoque())
        );
    }


    @Test
    void testarValidacaoNomeProdutoVazio() {

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1, null, 10.40, 5);
        });
        assertEquals("O campo: nome não pode ser nulo ou vazio.", ex.getMessage());

    }

    @Test
    void testarValidacaoNomeProdutoNulo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new Produto(1, " ", 10.40, 5);
        });
        assertEquals("O campo: nome não pode ser nulo ou vazio.", ex.getMessage());
    }

}





