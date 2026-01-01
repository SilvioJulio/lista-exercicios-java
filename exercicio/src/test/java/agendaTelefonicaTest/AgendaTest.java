package agendaTelefonicaTest;

import org.academiadb.agendaTelefonica.AgendaTelefonica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class AgendaTest {
    @Test

    public void deveAdicionarContatoTest() {
        AgendaTelefonica agenda = new AgendaTelefonica();
        agenda.addicionarContato("Silvio Julio", "71997377059");

        assertAll(
            () -> Assertions.assertTrue(agenda.listarContatos().contains("Silvio Julio")),
            () -> Assertions.assertTrue(agenda.listarContatos().contains("71997377059"))
        );
    }

    @Test
    public void devListarContatosTest() {
        AgendaTelefonica agenda = new AgendaTelefonica();
        agenda.addicionarContato("Ana Maria", "71999990001");
        agenda.addicionarContato("Carlos Silva", "71999990002");

        String lista = agenda.listarContatos();
        assertAll(
                () -> Assertions.assertTrue(lista.contains("Ana Maria")),
                () -> Assertions.assertTrue(lista.contains("71999990001")),
                () -> Assertions.assertTrue(lista.contains("Carlos Silva")),
                () -> Assertions.assertTrue(lista.contains("71999990002"))
        );

    }


}
