package org.academiadb.automovel;

public class MotoPartidaPedal extends Moto{

    private boolean aceleradorPuxado;
    private TipoTransmissao transmissao;

    public MotoPartidaPedal(String modelo, String cor, int anoFabricacao, String marca, TipoTransmissao transmissa, String placa, boolean ligado) {
        super(modelo, cor, anoFabricacao, marca, placa, ligado, transmissa);
        this.transmissao = transmissa;
    }


    public void puxarAcelerador() { this.aceleradorPuxado = true; }



    @Override
    public void ligar() {
        if (!isLigado() && aceleradorPuxado) {
            super.ligar(); // só liga se o acelerador estiver puxado
        }
    }

}
