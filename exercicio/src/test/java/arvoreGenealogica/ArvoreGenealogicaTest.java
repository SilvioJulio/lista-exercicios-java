package arvoreGenealogica;

import org.academiadb.arvoreGenealogica.ArvoreGenealogica;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ArvoreGenealogicaTest {


    @Test
    @DisplayName("Dados válidos corretamente")
    void deveCriarComDadosValidos() {
        ArvoreGenealogica arvore = new ArvoreGenealogica(
                "Felipe e Marcos", 80,
                "Lucas e Sandra", 78,
                "Renata", 35,
                "Luiz Felipe", 44,
                "Arthur Julio", 10
        );

        assertEquals("Luiz Felipe", arvore.getNomePai());
        assertEquals("Renata", arvore.getNomeMae());
        assertEquals("Arthur Julio", arvore.getNomeFilho());
    }

    @Test
    @DisplayName("Ocorrer a falhar, caso a  idade do pai < idade do filho + 12")
    void idadePaiInsuficiente() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Felipe e Marcos", 70,
                        "Lucas e Sandra", 68,
                        "Renata", 25,
                        "Luiz", 30,
                        "Arthur", 20
                )
        );
    }

    @Test
    @DisplayName("Falhar se idade da mãe < idade do filho + 12")
    void idadeMaeInsuficiente() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Felipe e Marcos", 70,
                        "Lucas e Sandra", 68,
                        "Renata", 28,
                        "Luiz", 40,
                        "Arthur", 20
                )
        );
    }

    @Test
    @DisplayName("Deve falhar se avós paternos < pai + 25 (quando idade dos avós é fornecida)")
    void avosPaternosInsuficientes() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Felipe e Marcos", 60,  // avô 60
                        "Lucas e Sandra", 70,
                        "Renata", 40,
                        "Luiz", 50,
                        "Arthur", 15
                )
        );
    }

    @Test
    @DisplayName("Deve rejeitar nomes com dígitos")
    void nomeInvalidoComDigitos() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Felipe 123", null,
                        "Lucas e Sandra", null,
                        "Renata", 35,
                        "Luiz Felipe", 44,
                        "Arthur Julio", 10
                )
        );
    }

    @Test
    @DisplayName("Deve normalizar múltiplos espaços nos nomes")
    void normalizaEspacos() {
        ArvoreGenealogica arvore = new ArvoreGenealogica(
                "Felipe   e   Marcos", null,
                "Lucas    e    Sandra", null,
                "Renata   Maria", 35,
                "Luiz    Felipe", 44,
                "Arthur   Julio", 10
        );
        assertEquals("Felipe e Marcos", arvore.getAvosPaternosNome());
        assertEquals("Lucas e Sandra", arvore.getAvosMaternosNome());
        assertEquals("Renata Maria", arvore.getNomeMae());
        assertEquals("Luiz Felipe", arvore.getNomePai());
        assertEquals("Arthur Julio", arvore.getNomeFilho());
    }

}
