package org.academiadb.controleEmprestimo;

import java.util.regex.Pattern;

public class Pessoa {

    private static final Pattern CONTATO = Pattern.compile("^\\(\\d{2}\\)\\s?\\d{8,9}$");
    String nome;
    int idade;
    String sexo;
    String contato;
    String endereco;


    public Pessoa(String nome, int idade, String sexo, String contato, String endereco) {

        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.contato = contato;
        this.endereco = endereco;

        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");

        if (contato == null || contato.isBlank() || !CONTATO.matcher(contato).matches()) {
            throw new IllegalArgumentException("Contato é obrigatório e deve seguir o formato BR, ex: (DDD) numero ");
        }

    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getContato() {
        return contato;
    }

    public String getEndereco() {
        return endereco;
    }

}
