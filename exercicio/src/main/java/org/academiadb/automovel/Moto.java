package org.academiadb.automovel;

public class Moto extends Automovel implements Carregavel{
    private TipoTransmissao transmissao;

    public Moto(String modelo, String cor, int anoFabricacao, String marca, String placa, boolean ligado, TipoTransmissao transmissao) {
        super(modelo, cor, anoFabricacao, marca, placa, ligado);
        this.transmissao = transmissao;
    }

    public TipoTransmissao getTransmissao() {
        return transmissao;
    }

    public String informacaoMoto(){
        return "====== Informação do Moto ======\n"+
                "modelo: '" + this.getModelo() +"\n"+
                "Cor: "+ this.getCor() +"\n"+
                "Ano Fabricaçãoao:"+ this.getAnoFabricacao() +"\n"+
                "Marca:"+ this.getModelo()+"\n"+
                "Placa:" + this.getModelo() +"\n"+
                "Ligado" + this.isLigado() +"\n"+
                "Estado=" + (this.isLigado() ? "ligado" : "desligado");

    }

    @Override
    public String getIdentificacao() {
        return "Moto placa " + getPlaca();
    }

}
