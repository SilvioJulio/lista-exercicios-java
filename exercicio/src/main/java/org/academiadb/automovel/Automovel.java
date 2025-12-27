package org.academiadb.automovel;

import static org.academiadb.automovel.ValidarDadosAutomovel.validarCarroAutomovel;
import static org.academiadb.automovel.ValidarDadosAutomovel.validarPlacaAutomovel;

public class Automovel {
    private  String modelo;
    private  String cor;
    private  int anoFabricacao;
    private  String marca;
    private  String placa;
    private boolean ligado ;

    public Automovel(String modelo, String cor, int anoFabricacao, String marca, String placa, boolean ligado) {
        this.modelo = validarCarroAutomovel(modelo, "modelo");
        this.cor =  validarCarroAutomovel(cor, "cor");
        this.anoFabricacao =anoFabricacao;
        this.marca =  validarCarroAutomovel(marca, "marca");
        this.placa =  validarPlacaAutomovel(placa);
        this.ligado = ligado;
    }


    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void ligar () {
        if (!ligado) {
            ligado = true;
        }
    }

    public void desligar(){
        if (ligado){
            ligado = false;
        }
    }

    public String informacaoAutomovel(){
        return "====== Informação do Automovel ======\n"+
                "modelo: '" + this.getModelo() +"\n"+
                "Cor: "+ this.getCor() +"\n"+
                "Ano Fabricaçãoao: "+ this.anoFabricacao +"\n"+
                "Marca: "+ this.modelo +"\n"+
                "Placa: " + this.placa +"\n"+
                "Estado: " + (ligado ? "ligado" : "desligado");

    }


}


