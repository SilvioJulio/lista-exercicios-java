package org.academiadb.agendaTelefonica;


public class ValidarContatos {
    public static String validarNome(String nome, String campo) {
        if (nome == null || nome.isBlank()) {
            return "Nome inválido.";
        }
        // Aceita alfanumérico, espaço e hífen
        String regex = "^[\\p{L}\\s\\d]+$";
        if (!nome.matches(regex)) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve conter letras, números e hífen (-).");
        }

        if (nome.length() < 2) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve ter pelo menos 3 caracteres.");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve ter no máximo 100 caracteres.");
        }

        // Normaliza espaços múltiplos
        return nome.replaceAll("\\s+", " ");

    }

    public static String validarTelefone(String telefone, String campo) {
        if (telefone == null || telefone.isBlank()) {
            return "Telefone inválido.";
        }

        // Aceita alfanumérico, espaço e hífen
        String regex = "^\\d+$";
        if (!telefone.matches(regex)) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve conter letras, números");
        }

        if (telefone.length() < 2) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve ter pelo menos 2 caracteres.");
        }
        if (telefone.length() > 11) {
            throw new IllegalArgumentException("Campo '" + campo + "Telefone deve ser celular com dígito 11 (ex.: 7199...).");
        }

        // Normaliza espaços múltiplos
        return telefone.replaceAll("\\s+", " ");
    }


}




