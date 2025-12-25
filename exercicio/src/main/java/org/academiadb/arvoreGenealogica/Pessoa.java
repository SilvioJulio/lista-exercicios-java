
package org.academiadb.arvoreGenealogica;

import static org.academiadb.arvoreGenealogica.ValidarNomeIdade.*;

public class Pessoa {

    private final String avosPaternosNome;
    private final Integer avosPaternosIdade;  // opcional: se quiser validar idade dos avós
    private final String avosMaternosNome;
    private final Integer avosMaternosIdade;  // opcional
    private final String maeNome;
    private final int maeIdade;
    private final String paiNome;
    private final int paiIdade;
    private final String filhoNome;
    private final int filhoIdade;


    public Pessoa(String avosPaternosNome,
                  Integer avosPaternosIdade,
                  String avosMaternosNome,
                  Integer avosMaternosIdade,
                  String nomeMae,
                  int maeIdade,
                  String paiNome,
                  int paiIdade,
                  String filhoNome,
                  int filhoIdade) {

        // método validarNomes verifica e faz o tratamento de erros dos campos nomes
        this.avosPaternosNome = validarNomes(avosPaternosNome, "avosPaternosNome");
        this.avosMaternosNome = validarNomes(avosMaternosNome, "avosMaternosNome");
        this.maeNome = validarNomes(nomeMae,          "maeNome:");
        this.paiNome = validarNomes(paiNome,          "paiNome:");
        this.filhoNome = validarNomes(filhoNome,        "filhoNome");

        // método validarIdade verifica e faz o tratamento de erros dos campos idade
        this.maeIdade = validarIdade(maeIdade,   "maeIdad");
        this.paiIdade = validarIdade(paiIdade,   "paiIdade");
        this.filhoIdade = validarIdade(filhoIdade, "filhoIdade");
        this.avosMaternosIdade = validarIdade(avosMaternosIdade, "avosMaternosIdade");
        this.avosPaternosIdade = validarIdade(avosPaternosIdade, "avosPaternosIdade");


        // Regras de negócio
        validarIdadeMinimaPais(this.maeIdade, this.paiIdade);
        validarNomesDistintos(this.paiNome, this.maeNome, this.filhoNome);


        validarIdadeMinimaPais(this.maeIdade, this.paiIdade);
        validarNomesDistintos(this.paiNome, this.maeNome, this.filhoNome);

        // Pais devem ser ≥ idade do filho + GAP mínimo
        validarPaisFilho(this.maeIdade, this.paiIdade, this.filhoIdade);

        // Avós (se informados) ≥ idade dos pais + GAP dos avós
        validarAvosPais(this.avosPaternosIdade, this.avosMaternosIdade, this.paiIdade, this.maeIdade);



    }



    public String getAvosPaternosNome() { return avosPaternosNome; }
    public Integer getAvosPaternosIdade() { return avosPaternosIdade; }
    public String getAvosMaternosNome() { return avosMaternosNome; }
    public Integer getAvosMaternosIdade() { return avosMaternosIdade; }
    public String getMaeNome() { return maeNome; }
    public int getMaeIdade() { return maeIdade; }
    public String getPaiNome() { return paiNome; }
    public int getPaiIdade() { return paiIdade; }
    public String getFilhoNome() { return filhoNome; }
    public int getFilhoIdade() { return filhoIdade; }

}
