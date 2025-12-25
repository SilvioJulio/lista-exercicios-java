package org.academiadb.arvoreGenealogica;

public class ArvoreGenealogica  extends Pessoa{


    public ArvoreGenealogica(String avosPaternosNome, Integer avosPaternosIdade, String avosMaternosNome, Integer avosMaternosIdade, String nomeMae, int maeIdade, String nomePai, int paiIdade, String nomeFilho, int filhoIdade) {
        super(avosPaternosNome, avosPaternosIdade, avosMaternosNome, avosMaternosIdade, nomeMae, maeIdade, nomePai, paiIdade, nomeFilho, filhoIdade);
    }

    public String exibirArvoreGenealógica(){

        return "====== árvore genealógica  ======\n" +
                "Avós Maternos: " + this.getAvosMaternosNome() + "\n"+
                "Idade avós maternos: " + this.getAvosMaternosIdade() + "\n"+
                "Avós Paternos: " +this.getAvosPaternosNome()+ "\n"+
                "Idade avós maternos: " + this.getAvosPaternosIdade() + "\n"+
                "Mãe: "+this.getMaeNome()+"\n"+
                "Idade: "+this.getMaeIdade()+ "\n"+
                "Pai: "+this.getPaiNome()+"\n"+
                "Idade: "+this.getPaiIdade()+ "\n"+
                "Filho: "+this.getFilhoNome()+"\n"+
                "Idade: "+this.getFilhoIdade()+"\n"
                ;

    }


}
