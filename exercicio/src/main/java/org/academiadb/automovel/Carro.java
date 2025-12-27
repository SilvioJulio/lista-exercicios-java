package org.academiadb.automovel;

public class Carro extends Automovel implements Carregavel{

    private TipoTransmissao transmissao;

    public Carro(String modelo, String cor, int anoFabricacao, String marca, String placa, boolean ligado, TipoTransmissao transmissao) {
        super(modelo, cor, anoFabricacao, marca, placa, ligado);
        this.transmissao = transmissao;

    }

    public TipoTransmissao getTransmissao() {
        return transmissao;
    }

    @Override
    public String getIdentificacao() {
        return "Carro placa: " + getPlaca();
    }

}
