import org.academiadb.questao16.Emprestimo;
import org.academiadb.questao16.Livro;
import org.academiadb.questao16.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTest {

    private static final DateTimeFormatter FORMATTER_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    public void criandoEmprestimoTest(){
        Pessoa leitor = new Pessoa("Lucas",28, "M","(71) 988007789", "Rua B Santso Silva, n° 89");
        Livro livro = new Livro("O Poder do Hábito", "Charles Duhigg", "Autoajuda");
        LocalDate hoje = LocalDate.of(2025, 12, 21);

        LocalDate devolucao = hoje.plusDays(3);

        Emprestimo emprestimo = new Emprestimo( leitor, livro,hoje, devolucao );

        String detalhes = emprestimo.detalhesEmprestimo();

        assertNotNull(detalhes);
        assertAll(
                () ->   assertTrue(detalhes.contains("O Poder do Hábito") ,"Detalhes devem conter o título do livro"),
                () -> assertTrue(detalhes.contains("Charles Duhigg"), "Detalhes devem conter o autor do livro"),
                () ->  assertTrue(detalhes.contains("Lucas"), "Detalhes devem conter o nome do leitor"),
                () ->  assertTrue(detalhes.contains("(71) 988007789"), "Detalhes devem conter o contato do leitor")
        );


        String dataEmprestimoFormatada = hoje.format(FORMATTER_BR);      // 10/01/2025
        String dataDevolucaoFormatada = devolucao.format(FORMATTER_BR);



        assertTrue(detalhes.contains(dataEmprestimoFormatada), "Detalhes devem conter a data de empréstimo formatada");
         assertTrue(detalhes.contains(dataDevolucaoFormatada), "Detalhes devem conter a data de devolução formatada");



    }
    @Test
    void naoDevePermitirDataDevolucaoAntesDaDataEmprestimo() {
        Pessoa leitor = new Pessoa("Fernanda", 17, "F", "(71) 94491122", "Rua Carlos Gomes, nº 34");
        Livro livro = new Livro("O Senhor das Moscas", "William Golding", "Ficção");

        LocalDate emprestimo = LocalDate.of(2025, 12, 21);
        LocalDate devolucao  = LocalDate.of(2025, 12, 20); // <-- devolução ANTES do empréstimo

        assertThrows(IllegalArgumentException.class,
                () -> new Emprestimo(leitor, livro, emprestimo, devolucao),
                "Devolução antes do empréstimo deve lançar IllegalArgumentException"
        );

    }


    @Test
    void deveInformarAtrasoNaDevoluçao() {
        Pessoa leitor = new Pessoa("Fábio Silva", 30, "M", "(74) 955112277", "Endereço");
        Livro livro = new Livro("Orgulho e Preconceito", "Jane Austen", "Romance");

        LocalDate emprestimo = LocalDate.of(2025, 12, 21);
        LocalDate devolucao = LocalDate.of(2025, 12, 30);
        Emprestimo e = new Emprestimo(leitor, livro, emprestimo, devolucao);


        assertNotNull(e.detalhesEmprestimo());
    }




}

