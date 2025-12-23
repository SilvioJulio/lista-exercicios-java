
package org.academiadb.arvoreGenealogica;

import static org.academiadb.arvoreGenealogica.ValidarNomeIdade.*;

public class Pessoa {

    private final String avosPaternosNome;
    private final Integer avosPaternosIdade;  // opcional: se quiser validar idade dos avós
    private final String avosMaternosNome;
    private final Integer avosMaternosIdade;  // opcional
    private final String nomeMae;
    private final int idadeMae;
    private final String nomePai;
    private final int idadePai;
    private final String nomeFilho;
    private final int idadeFilho;


    public Pessoa(String avosPaternosNome,
                  Integer avosPaternosIdade,
                  String avosMaternosNome,
                  Integer avosMaternosIdade,
                  String nomeMae,
                  int idadeMae,
                  String nomePai,
                  int idadePai,
                  String nomeFilho,
                  int idadeFilho) {

        // método validarNomes verifica e faz o tratamento de erros dos campos nomes
        this.avosPaternosNome = validarNomes(avosPaternosNome, "avosPaternosNome");
        this.avosPaternosIdade = avosPaternosIdade;
        this.avosMaternosNome = validarNomes(avosMaternosNome, "avosMaternosNome");
        this.avosMaternosIdade = avosMaternosIdade;
        this.nomeMae          = validarNomes(nomeMae,          "nomeMae");
        this.nomePai          = validarNomes(nomePai,          "nomePai");
        this.nomeFilho        = validarNomes(nomeFilho,        "nomeFilho");

        // método validarIdade verifica e faz o tratamento de erros dos campos idade
        this.idadeMae   = validarIdade(idadeMae,   "idadeMae");
        this.idadePai   = validarIdade(idadePai,   "idadePai");
        this.idadeFilho = validarIdade(idadeFilho, "idadeFilho");


        // Regras de negócio
        validarIdadeMinimaPais(this.idadeMae, this.idadePai);
        validarNomesDistintos(this.nomePai, this.nomeMae, this.nomeFilho);


        validarIdadeMinimaPais(this.idadeMae, this.idadePai);
        validarNomesDistintos(this.nomePai, this.nomeMae, this.nomeFilho);

        // Pais devem ser ≥ idade do filho + GAP mínimo
        validarPaisFilho(this.idadeMae, this.idadePai, this.idadeFilho);

        // Avós (se informados) ≥ idade dos pais + GAP dos avós
        validarAvosPais(this.avosPaternosIdade, this.avosMaternosIdade, this.idadePai, this.idadeMae);



    }



    public String getAvosPaternosNome() { return avosPaternosNome; }
    public Integer getAvosPaternosIdade() { return avosPaternosIdade; }
    public String getAvosMaternosNome() { return avosMaternosNome; }
    public Integer getAvosMaternosIdade() { return avosMaternosIdade; }
    public String getNomeMae() { return nomeMae; }
    public int getIdadeMae() { return idadeMae; }
    public String getNomePai() { return nomePai; }
    public int getIdadePai() { return idadePai; }
    public String getNomeFilho() { return nomeFilho; }
    public int getIdadeFilho() { return idadeFilho; }

}
