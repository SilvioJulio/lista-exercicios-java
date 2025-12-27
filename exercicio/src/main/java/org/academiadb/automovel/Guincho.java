package org.academiadb.automovel;

import java.util.Optional;

public class Guincho extends Automovel implements  Carregavel{

    private Carregavel veiculoCarregado;

    public Guincho(String modelo, String cor, int anoFabricacao, String marca, String placa, boolean ligado) {
        super(modelo, cor, anoFabricacao, marca, placa, ligado);
    }


    public Optional<Carregavel> getVeiculoCarregado() {
        return Optional.ofNullable(veiculoCarregado);
    }

    public void carregar(Carregavel veiculo) {
        if (veiculoCarregado != null) {
            throw new IllegalStateException("Já existe um veículo carregado.");
        }
        // restrição do domínio: guincho só carrega Carro ou Moto
        if (!(veiculo instanceof Carro) && !(veiculo instanceof Moto)) {
            throw new IllegalArgumentException("O guincho só pode carregar Carro ou Moto.");
        }
        this.veiculoCarregado = veiculo;
    }

    public Carregavel descarregar() {
        Carregavel v = this.veiculoCarregado;
        this.veiculoCarregado = null;
        return v;
    }


    @Override
    public String getIdentificacao() {
        return "";
    }

    public String informacaoGuincho(){
        return "====== Informação do Guincho ======\n"+
                "modelo: '" + this.getModelo() +"\n"+
                "Cor: "+ this.getCor() +"\n"+
                "Ano Fabricaçãoao: "+ this.getAnoFabricacao() +"\n"+
                "Marca: "+ this.getModelo()+"\n"+
                "Placa: " + this.getModelo() +"\n"+
                "Ligado: " + this.isLigado() +"\n"+
                "Estado: " + (this.isLigado() ? "ligado" : "desligado")+"\n"+
                "****** Usando plataforma do guincho *****\n"+
                "Status da plataforma: " +(this.veiculoCarregado  == null ? "sem carga" : "carregando ");


    }
}
