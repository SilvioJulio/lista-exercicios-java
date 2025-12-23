package org.academiadb.arvoreGenealogica;

public final class ValidarNomeIdade {


        private static final int MIN_IDADE_PAIS = 12;         // idade mínima aceitável para pai/mãe
        private static final int MAX_IDADE_GERAL = 125;       // teto geral de idade
        private static final int MIN_GAP_PARENT = 12;         // diferença mínima pai/mãe vs filho
        private static final int IDADE_MIN_AVOS = 25;    // diferença mínima avós vs pais


        public static String validarNomes(String valor, String campo) {
            if (valor == null) {
                throw new IllegalArgumentException("Campo '" + campo + "' nome é obrigatório e não pode ser nulo.");
            }
            String nome = valor.trim();
            if (nome.isEmpty()) {
                throw new IllegalArgumentException("Campo '" + campo + "' nome é obrigatório e não pode estar vazio.");
            }

            // Letras (Unicode) + espaços
            String regex = "^[\\p{L}\\s]+$";
            if (!nome.matches(regex)) {
                throw new IllegalArgumentException("Campo '" + campo + "' deve conter apenas letras e espaços.");
            }

            if (nome.length() < 2) {
                throw new IllegalArgumentException("Campo '" + campo + "' deve ter pelo menos 2 caracteres.");
            }
            if (nome.length() > 100) {
                throw new IllegalArgumentException("Campo '" + campo + "' deve ter no máximo 100 caracteres.");
            }

            // Normaliza espaços múltiplos
            return nome.replaceAll("\\s+", " ");
        }


        public static int validarIdade(int idade, String campo) {
            if (idade < 0) {
                throw new IllegalArgumentException("Campo '" + campo + "' idade não deve ser negativo.");
            }
            if (idade > MAX_IDADE_GERAL) {
                throw new IllegalArgumentException("Campo '" + campo + "' idade inválida (maior que " + MAX_IDADE_GERAL + ").");
            }
            return idade;
        }

        public static void validarIdadeMinimaPais(int idadeMae, int idadePai) {
            if (idadeMae < MIN_IDADE_PAIS) {
                throw new IllegalArgumentException("Regra: 'idadeMae' deve ser >= " + MIN_IDADE_PAIS + ".");
            }
            if (idadePai < MIN_IDADE_PAIS) {
                throw new IllegalArgumentException("Regra: 'idadePai' deve ser >= " + MIN_IDADE_PAIS + ".");
            }
        }


        public static void validarPaisFilho(int idadeMae, int idadePai, int idadeFilho) {
            if (idadeMae < idadeFilho + MIN_GAP_PARENT) {
                throw new IllegalArgumentException(
                        "Regra: 'idadeMae' deve ser pelo menos " + MIN_GAP_PARENT +
                                " anos maior que 'idadeFilho'. Recebidos: idadeMae=" + idadeMae + ", idadeFilho=" + idadeFilho
                );
            }
            if (idadePai < idadeFilho + MIN_GAP_PARENT) {
                throw new IllegalArgumentException(
                        "Regra: 'idadePai' deve ser pelo menos " + MIN_GAP_PARENT +
                                " anos maior que 'idadeFilho'. Recebidos: idadePai=" + idadePai + ", idadeFilho=" + idadeFilho
                );
            }
        }


        public static void validarAvosPais(Integer idadeAvosPaternos, Integer idadeAvosMaternos,
                                              int idadePai, int idadeMae) {
            if (idadeAvosPaternos != null && idadeAvosPaternos < idadePai + IDADE_MIN_AVOS) {
                throw new IllegalArgumentException(
                        "Regra: 'idadeAvosPaternos' deve ser pelo menos " + IDADE_MIN_AVOS +
                                " anos maior que 'idadePai'. Recebidos: idadeAvosPaternos=" + idadeAvosPaternos + ", idadePai=" + idadePai
                );
            }
            if (idadeAvosMaternos != null && idadeAvosMaternos < idadeMae + IDADE_MIN_AVOS) {
                throw new IllegalArgumentException(
                        "Regra: 'idadeAvosMaternos' deve ser pelo menos " + IDADE_MIN_AVOS +
                                " anos maior que 'idadeMae'. Recebidos: idadeAvosMaternos=" + idadeAvosMaternos + ", idadeMae=" + idadeMae
                );
            }
        }


        public static void validarNomesDistintos(String nomePai, String nomeMae, String nomeFilho) {
            if (nomePai.equalsIgnoreCase(nomeMae)) {
                throw new IllegalArgumentException("Regra: 'Nomes pai' e 'Mae' não devem ser iguais.");
            }
            if (nomePai.equalsIgnoreCase(nomeFilho)) {
                throw new IllegalArgumentException("Regra: 'Nomes pai' e 'Filho' não devem ser iguais.");
            }
            if (nomeMae.equalsIgnoreCase(nomeFilho)) {
                throw new IllegalArgumentException("Regra: 'Nomes Mãe' e 'Filho' não devem ser iguais.");
            }
        }
    }



