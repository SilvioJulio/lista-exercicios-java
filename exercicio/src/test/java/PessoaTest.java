import org.academiadb.controleEmprestimo.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    @Test
    void validarLeitor() {
        Pessoa leitor = new Pessoa("Luiz Silva", 23, "M", "(71) 998914562", "Rua João Silva, 34");
        assertEquals("Luiz Silva", leitor.getNome());
        assertEquals("(71) 99891456", leitor.getContato());


    }

    @Test
    void verificarContatoLeitor() {
        Pessoa leitor = new Pessoa("Luiz Silva", 23, "M", "(71) 99891-4562", "Rua João Silva, 34");
        assertEquals("(71) 99891-4562", leitor.getContato());


    }


}