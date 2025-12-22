import org.academiadb.questao16.Livro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LivroTest {

    @Test
    void deveCriarLivroComDadosCorretos() {
        Livro livro1 = new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia");
        assertEquals("O Hobbit", livro1.getTitulo());
        assertEquals("J.R.R. Tolkien", livro1.getAutor());
        assertEquals("Fantasia", livro1.getGenero());
    }

    @Test
    void tituloNaoDeveSerVazio() {
        Livro livro = new Livro("Orgulho e Preconceito", "Jane Austen", "Romance");
        assertThrows(IllegalArgumentException.class, () -> new Livro("", "Autor", "Categoria"));
        assertEquals("Orgulho e Preconceito", livro.getTitulo());
    }

}
