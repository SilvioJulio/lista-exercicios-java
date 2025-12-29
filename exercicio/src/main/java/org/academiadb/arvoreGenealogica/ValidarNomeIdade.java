package org.academiadb.arvoreGenealogica;

public final class ValidarNomeIdade {


        private static final int MIN_IDADE_PAIS = 16;        // idade mínima aceitável para pai/mãe
        private static final int MAX_IDADE_GERAL = 125;      // teto geral de idade
        private static final int MIN_DIF_PAIS = 12;          // evitar que idade do filho, fique maior que aos dos pais
        private static final int IDADE_MIN_AVOS = 25;        // diferença mínima avós vs pais



    public static String validarNomes(String valor, String campo) {
        if (campo == null) {
            throw new IllegalArgumentException("Campo '" + campo + "' é obrigatório e não pode ser nulo.");
        }
        if (valor == null) {
            throw new IllegalArgumentException("Campo '" + campo + "' é obrigatório e não pode ser nulo.");
        }

        String nome = valor.trim();
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Campo '" + campo + "' não pode ser vazio.");
        }

        // Apenas letras (Unicode) e espaços
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

        public static void validarIdadeMinimaPais(int maeIdade, int PaiIdade) {
            if (maeIdade < MIN_IDADE_PAIS) {
                throw new IllegalArgumentException("Regra: 'idade da mãe' deve ser >= " + MIN_IDADE_PAIS + ".");
            }
            if (PaiIdade < MIN_IDADE_PAIS) {
                throw new IllegalArgumentException("Regra: 'Idade do pai' deve ser >= " + MIN_IDADE_PAIS + ".");
            }
        }


        public static void validarPaisFilho( int maeIdade, int paiIdade, int filhoIdade  ) {
            if (maeIdade < filhoIdade + MIN_DIF_PAIS) {
                throw new IllegalArgumentException(
                        "Regra: 'Mae' deve ter idade " + MIN_DIF_PAIS +
                                " anos maior que 'idade do seu filho'. Recebidos: Idade da mãe:" + maeIdade + ", => filho idade=" + filhoIdade
                );
            }
            if (paiIdade < filhoIdade + MIN_DIF_PAIS) {
                throw new IllegalArgumentException(
                        "Regra: 'idadePai' deve ser pelo menos " + MIN_DIF_PAIS +
                                " anos maior que 'idade do filho'. Recebidos: Idade do pai =>" + paiIdade + ",filho idade" + filhoIdade
                );
            }
        }


        public static void validarAvosPais(Integer idadeAvosPaternos, Integer idadeAvosMaternos,
                                              int idadePai, int idadeMae) {
            if (idadeAvosPaternos != null && idadeAvosPaternos < idadePai + IDADE_MIN_AVOS) {
                throw new IllegalArgumentException(
                        "Regra: 'idadeAvosPaternos' deve ser pelo menos " + IDADE_MIN_AVOS +
                                " anos maior que 'idadePai'. Recebidos: idadeAvosPaternos=" + idadeAvosPaternos + ",  idade do pai=" + idadePai
                );
            }
            if (idadeAvosMaternos != null && idadeAvosMaternos < idadeMae + IDADE_MIN_AVOS) {
                throw new IllegalArgumentException(
                        "Regra: 'idadeAvosMaternos' deve ser pelo menos " + IDADE_MIN_AVOS +
                                " anos maior que 'idadeMae'. Recebidos: idadeAvosMaternos=" + idadeAvosMaternos + ", idade da mãe=" + idadeMae
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



