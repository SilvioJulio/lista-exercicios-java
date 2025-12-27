package org.academiadb.automovel;



public class CarroAutomatico extends Carro {

    private boolean freioPressionado;
    private TipoTransmissao transmissao;

    public CarroAutomatico(String modelo, String cor, int anoFabricacao, String marca, String placa, boolean ligado, TipoTransmissao transmissao) {
        super(modelo, cor, anoFabricacao, marca, placa, ligado, transmissao);


        this.transmissao = transmissao;

    }


    public void pressionarFreio() { this.freioPressionado = true; }

    public TipoTransmissao getTransmissao() {
        return transmissao;
    }

    @Override
    public void ligar() {
        if(!isLigado() && freioPressionado) {
            super.ligar();
        }
    }
}
