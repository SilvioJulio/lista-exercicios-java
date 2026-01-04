package superMercadoTest;

import org.academiadb.prova.Pedido;
import org.academiadb.prova.Produto;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void deveCalcularTrocoInsuficienteDeveRetornarNegativoEMensagem() {
        Pedido p = new Pedido();
        Produto prod = new Produto(1, "Produto Teste", 10.00, 5);
        p.adicionarItemNaLista(prod, 2); // total 20.00

        double troco = p.calcularTroco(15.00);
        assertEquals(-1.0, troco, 0.0001);
    }

    @Test
    void deveCalcularMenorQuantidadeCedulasEMoedasDeveDistribuirCorretamente() {
        Pedido p = new Pedido();
        double troco = 186.73;
        Map<String, Integer> dist = p.calcularMenorQuantidadeDeCedulasEMoedas(troco);

        // Esperado:
        // 100:1, 50:1, 20:1, 10:1, 5:1, 1:1, 0.50:1, 0.10:2, 0.01:3
        assertEquals(1, dist.getOrDefault("R$ 100", 0));
        assertEquals(1, dist.getOrDefault("R$ 50", 0));
        assertEquals(1, dist.getOrDefault("R$ 20", 0));
        assertEquals(1, dist.getOrDefault("R$ 10", 0));
        assertEquals(1, dist.getOrDefault("R$ 5", 0));
        assertEquals(1, dist.getOrDefault("R$ 1", 0));
        assertEquals(1, dist.getOrDefault("R$ 0.50", 0));
        assertEquals(2, dist.getOrDefault("R$ 0.10", 0));
        assertEquals(3, dist.getOrDefault("R$ 0.01", 0));
    }

    @Test
    void deveCalcularMenorQuantidadeComTrocoNegativoDeveRetornarMapaVazio() {
        Pedido p = new Pedido();
        Map<String, Integer> dist = p.calcularMenorQuantidadeDeCedulasEMoedas(-5.0);
        assertTrue(dist.isEmpty());
    }

    @Test
    void deveCalcularTrocoExatoDeveRetornarZero() {
        Pedido p = new Pedido();
        Produto prod = new Produto(1, "Produto Teste", 10.00, 5);
        p.adicionarItemNaLista(prod, 2); // total 20.00

        double troco = p.calcularTroco(20.00);
        assertEquals(0.0, troco, 0.0001);
    }

    @Test
    void deveCalcularTrocoMaiorDeveRetornarValorCorreto() {
        Pedido p = new Pedido();
        Produto prod = new Produto(1, "Produto Teste", 15.00, 5);
        p.adicionarItemNaLista(prod, 2); // total 30.00

        double troco = p.calcularTroco(50.00);
        assertEquals(20.0, troco, 0.0001);
    }
}

