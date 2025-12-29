package org.academiadb.agendaTelefonica;

import static org.academiadb.agendaTelefonica.ValidarContatos.validarNome;
import static org.academiadb.agendaTelefonica.ValidarContatos.validarTelefone;

public class Contato {
    String nome;
    String telefone;

    public Contato(String nome, String telefone) {
        this.nome = validarNome(nome, "Nome");
        this.telefone = validarTelefone(telefone, "Telefone");
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
