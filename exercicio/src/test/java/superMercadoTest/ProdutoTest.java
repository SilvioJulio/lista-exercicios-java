package superMercadoTest;

import org.academiadb.prova.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoTest {

       @Test
    public void testarValidacaoProduto() {
           Produto produto = new Produto(23,"Arroz", 10.0, 5);
           assertAll (
                   () -> assertEquals(23, produto.getId()),
                   () -> assertEquals("Arroz", produto.getNome()),
                   () -> assertEquals(10.0, produto.getPreco()),
                   () -> assertEquals(5, produto.getQuantidadeEmEstoque())
           );


    }
}
