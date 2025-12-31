package superMercadoTest;

import org.academiadb.prova.Estoque;
import org.academiadb.prova.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EstoqueEstoque {



    @Test
    void naoPermiteIdDuplicado() {
        Estoque e = new Estoque();
        int id1 = e.gerarId();
        Produto p1 = new Produto(id1, "Feijão", 8.5, 10);
        assertTrue(e.cadastrarProduto(p1));

        Produto p2 = new Produto(id1, "Arroz", 7.0, 5);
        assertFalse(e.cadastrarProduto(p2));
    }

    @Test
    void cadastrarComIdNovoGeraIdUnico() {
        Estoque e = new Estoque();
        Produto p1 = new Produto(0, "Feijão", 8.5, 10);
        Produto p2 = new Produto(0, "Arroz", 7.0, 5);

        int id1 = e.cadastrarComIdNovo(p1);
        int id2 = e.cadastrarComIdNovo(p2);

        assertNotEquals(id1, id2);
        assertNotNull(e.encontraProdutoPorId(id1));
        assertNotNull(e.encontraProdutoPorId(id2));
    }
}
