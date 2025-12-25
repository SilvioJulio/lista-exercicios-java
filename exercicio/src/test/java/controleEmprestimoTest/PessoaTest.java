package controleEmprestimoTest;

import org.academiadb.controleEmprestimo.Pessoa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class PessoaTest {

    @Test
    void validarLeitor() {
        Pessoa leitor = new Pessoa("Luiz Silva", 23, "M", "(71) 977551122", "Rua João Silva, 34");
        assertEquals("Luiz Silva", leitor.getNome());
        assertEquals("(71) 977551122", leitor.getContato());


    }

    @Test
    void verificarContatoLeitor() {
        Pessoa leitor = new Pessoa("Luiz Silva", 23, "M", "(71) 998914562", "Rua João Silva, 34");
        assertEquals("(71) 998914562", leitor.getContato());


    }


}