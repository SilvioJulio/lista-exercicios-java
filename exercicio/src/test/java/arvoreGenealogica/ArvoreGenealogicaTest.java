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
                "Ivone", 60,
                "Sandra", 73,
                "Fernanda", 26,
                "Luiz Felipe", 25,
                "Arthur Julio", 7
        );

        assertEquals("Luiz Felipe", arvore.getPaiNome());
        assertEquals("Fernanda", arvore.getMaeNome());
        assertEquals("Arthur Julio", arvore.getFilhoNome());
    }

    @Test
    @DisplayName("Ocorrer a falhar, caso a  idade do pai < idade do filho + 12")
    void deveVerificarIdadePaiInsuficiente() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Viviane", 70,
                        "Lucas e Sandra", 68,
                        "Renata", 25,
                        "Luiz", 30,
                        "Arthur", 20
                )
        );
    }

    @Test
    @DisplayName("Falhar se idade da mãe < idade do filho + 12")
    void deveVerificarIdadeMaeInsuficiente() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Felipe e Marcos", 70,
                        "Lucas e Sandra", 71,
                        "Rebeca", 28,
                        "Luiz", 40,
                        "Lucas", 20
                )
        );
    }

    @Test
    @DisplayName("Deve falhar se avós paternos < pai + 25 (quando idade dos avós é fornecida)")
    void deveVerificarAvosPaternosInsuficientes() {
        assertThrows(IllegalArgumentException.class, () ->
                new ArvoreGenealogica(
                        "Marcos", 60,  // avô 60
                        "Lucas e Sandra", 70,
                        "Renata", 40,
                        "Luiz", 50,
                        "Arthur", 15
                )
        );
    }

    @Test
    @DisplayName("Deve rejeitar campos null nos nomes")
    void deveVerificarNomeInvalidoComDigitos() {
        assertThrows(NullPointerException.class, () ->
                new ArvoreGenealogica(
                        "Gisele", null,
                        "Lucas e Sandra", null,
                        "Renata", 35,
                        "Luiz Felipe", 44,
                        "Arthur Julio", 10
                )
        );
    }

    @Test
    @DisplayName("Deve normalizar múltiplos espaços nos nomes")
    void deveNormalizaEspacos() {
        ArvoreGenealogica arvore = new ArvoreGenealogica(
                "Marcos", 74,
                "Adriana", 70,
                "Renata Maria", 35,
                "Luiz Felipe", 44,
                "Arthur Julio", 10
        );
        assertEquals("Marcos", arvore.getAvosPaternosNome());
        assertEquals("Adriana", arvore.getAvosMaternosNome());
        assertEquals("Renata Maria", arvore.getMaeNome());
        assertEquals("Luiz Felipe", arvore.getPaiNome());
        assertEquals("Arthur Julio", arvore.getFilhoNome());
    }

}
