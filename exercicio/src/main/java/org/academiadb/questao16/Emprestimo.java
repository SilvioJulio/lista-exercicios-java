package org.academiadb.questao16;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo  {
    private Pessoa pessoa;
    private Livro livro;
    private final LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;


    public  Emprestimo( Pessoa pesssoa ,Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao ) {
        if (dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de empréstimo.");
        }

        this.pessoa = pesssoa;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao= dataDevolucao;
    }

    private static final DateTimeFormatter FORMATTER_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public String detalhesEmprestimo() {
        return "====== Detalhe Empréstimo ======\n" +
                "Nome: " + pessoa.getNome() + "\n" +
                "Idade: " + pessoa.getIdade() + "\n" +
                "Sexo: " + pessoa.getSexo() + "\n" +
                "Contato: " + pessoa.getContato() + "\n" +
                "Endereco: " + pessoa.getEndereco() + "\n" +
                "##### Livro #####\n" +
                "Título: " + livro.getTitulo() + "\n" +
                "Autor: " + livro.getAutor() + "\n" +
                "Gênero: " + livro.getGenero() + "\n" +
                "Numero Registro livro: " + livro.getNumeroRegistro() + "\n" +
                "Data empréstimo:" +   FORMATTER_BR.format(dataEmprestimo) +"\n"+
                "Data devolucao:  " +   FORMATTER_BR.format(dataDevolucao) +"\n";


    }

}
