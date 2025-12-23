package org.academiadb.arvoreGenealogica;

public class ArvoreGenealogica  extends Pessoa{


    public ArvoreGenealogica(String avosPaternosNome, Integer avosPaternosIdade, String avosMaternosNome, Integer avosMaternosIdade, String nomeMae, int idadeMae, String nomePai, int idadePai, String nomeFilho, int idadeFilho) {
        super(avosPaternosNome, avosPaternosIdade, avosMaternosNome, avosMaternosIdade, nomeMae, idadeMae, nomePai, idadePai, nomeFilho, idadeFilho);
    }

    public String exibirArvoreGenealógica(){

        return "====== árvore genealógica  ======\n" +
                "Avós Maternos: " + this.getAvosMaternosNome() + "\n"+
                "Idade avós maternos: " + this.getAvosMaternosIdade() + "\n"+
                "Avós Paternos: " +this.getAvosPaternosNome()+ "\n"+
                "Idade avós maternos: " + this.getAvosPaternosIdade() + "\n"+
                "Mãe: "+this.getNomeMae()+"\n"+
                "Idade: "+this.getIdadeMae()+ "\n"+
                "Pai: "+this.getNomePai()+"\n"+
                "Idade: "+this.getIdadePai()+ "\n"+
                "Filho: "+this.getNomeFilho()+"\n"+
                "Idade: "+this.getIdadeFilho()+"\n"
                ;

    }


}
