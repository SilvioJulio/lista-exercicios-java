package org.academiadb.controleEmprestimo;

public class Livro {
    private static int  ccntroleResgistroLivro = 100;
    private final int numeroRegistro ;
    String titulo;
    String autor;
    String genero;

    public Livro(String titulo, String autor, String genero) {

        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("O campo titulo, não pode ser vazio");
        if (autor == null || autor.isBlank()) throw new IllegalArgumentException("O campo autor, não pode ser vazio ");

        this.numeroRegistro = ccntroleResgistroLivro++;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;

    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
     }
}
