package org.academiadb.automovel;


public final class ValidarDadosAutomovel {

    private ValidarDadosAutomovel() { /* utilitária */ }


    public static String deveVerificarCamposAutomovel(String valor, String campo) {

        if (valor == null) {
            throw new IllegalArgumentException("Campo '" + campo + "' é obrigatório e não pode ser nulo.");
        }

        String nome = valor.trim();
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Campo '" + campo + "' é obrigatório e não pode estar vazio.");
        }

        // Aceita alfanumérico, espaço e hífen
        String regex = "^[\\p{L}\\d\\s-]+$";
        if (!nome.matches(regex)) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve conter letras, números e hífen (-).");
        }

        if (nome.length() < 2) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve ter pelo menos 8 caracteres.");
        }
        if (nome.length() > 20) {
            throw new IllegalArgumentException("Campo '" + campo + "' deve ter no máximo 100 caracteres.");
        }

        // Normaliza espaços múltiplos
        return nome.replaceAll("\\s+", " ");
    }

    public static String validarPlacaAutomovel(String placa) {
        if (placa == null) {
            throw new IllegalArgumentException("Placa é obrigatória e não pode ser nula.");
        }

        String placaMercoSul = placa.trim().toUpperCase();

        String regexPlaca = "^[A-Z]{3}-?\\d[A-Z\\d]\\d{2}$";
        if (!placaMercoSul.matches(regexPlaca)) {
            throw new IllegalArgumentException("Placa deve estar no formato 'ABC1234' com letras maiúsculas.");
        }

        return placaMercoSul;
    }


    public static void validarRegraAutomatica(boolean carroAutomatico, TipoTransmissao transmissao) {
        if (carroAutomatico && transmissao != TipoTransmissao.AUTOMATICA) {
            throw new IllegalArgumentException("Carro automático deve ter transmissão AUTOMÁTICA.");
        }
    }


}




