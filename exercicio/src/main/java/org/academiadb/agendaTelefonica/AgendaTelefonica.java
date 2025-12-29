package org.academiadb.agendaTelefonica;


import java.util.HashMap;
import java.util.Map;


public class AgendaTelefonica {
    Map<String, Contato> contatos;
    int cont = 0;

    public AgendaTelefonica() {
        this.contatos =  new HashMap<>(); // inicializa o Map para evitar NullPointerException


    }

    public String addicionarContato(String nome, String telefone) {



        for (Contato c : contatos.values()) {
            // pressupõe que Contato tem método getTelefone()
            if (contatos.containsKey(nome)) {
                return "==== Contato já existe ====\n" +
                        "Contato já cadastrado com este nome: " + nome;
            }
            if (telefone.equals(c.getTelefone())) {
                return "==== Telefone já em uso ====\n" +
                        "Outro contato já usa o telefone: " + telefone;
            }
        }


        // adiciona novo contato
        Contato contato = new Contato(nome, telefone);
        contatos.put(nome, contato);
        cont++;
        return "==== Contato adicionado ====\n" +
                "Nome: " + nome + " - Telefone: " + telefone;

    }

    public String listarContatos() {
        StringBuilder sb = new StringBuilder();
        sb.append("==== Lista de Contatos ====\n");

        if (contatos.isEmpty()) {
            sb.append("(sem contatos)\n");
        } else {
            for (Contato c : contatos.values()) {
                sb.append("Nome: ").append(c.getNome())
                        .append(" - Telefone: ").append(c.getTelefone()).append("\n");
            }
        }

        return sb.toString(); // nunca null
    }


    public String deletarContato(String nome) {
        Contato removido = contatos.remove(nome);
        if (removido != null) {
            return "==== Removendo Contato ====\n" +
                    "Deletado contato: " + nome;
        } else {
            return "Contato não encontrado: " + nome;
        }
    }

}