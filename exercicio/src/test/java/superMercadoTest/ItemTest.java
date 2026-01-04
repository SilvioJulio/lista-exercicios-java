package superMercadoTest;

import org.academiadb.prova.Item;
import org.academiadb.prova.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void deveCriarItem() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);
        Item item = new Item(produto, 4);

        assert item.getProduto() == produto;
        assert item.getQuantidade() == 4;
        assertEquals(34.0, item.getPrecoTotal());


    }
    @Test
    public void deveAlterarQuantidadeItemValida() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);
        Item item = new Item(produto, 4);

        item.setQuantidade(6); // Alterar para uma quantidade válida

        assert item.getQuantidade() == 6;
        assertEquals(51.0, item.getPrecoTotal());
    }
    @Test
    public void deveTesteAlterarQuantidadeItemInvalida() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);
        Item item = new Item(produto, 4);

        item.setQuantidade(-2); // Tentar alterar para uma quantidade inválida

        assert item.getQuantidade() == 4; // A quantidade deve permanecer inalterada
        assertEquals(34.0, item.getPrecoTotal());
    }

    @Test
    public void deveTesteToStringItem() {
        Produto produto = new Produto(1, "Feijão", 8.50, 10);
        Item item = new Item(produto, 4);

        String esperado = "Item{produto=Feijão, qtd=4, total=R$ 34.00}";
        assertEquals(esperado, item.toString());
    }

    
}
