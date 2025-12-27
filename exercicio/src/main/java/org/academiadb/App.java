package org.academiadb;


import org.academiadb.automovel.*;

import java.util.List;

public class App {



      void main() {


        {
            // Aqui você iniciaria a lógica principal do Programa: controle de empréstimo (ex: leitor, Instanciando novo objeto )


            CarroAutomatico carroAuto = new CarroAutomatico("Corolla", "Vemelho", 2023, "Gol", "ABC1D23", false, TipoTransmissao.AUTOMATICA);
            MotoPartidaPedal motoPedal = new MotoPartidaPedal("CG 160", "Vermelha", 2021, "Honda",TipoTransmissao.MANUAL, "FRT-4K11", false);
            Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93",false);

            // Polimorfismo: todos são Automovel, mas cada um tem comportamento próprio
            List<Automovel> frota = List.of(carroAuto, motoPedal, guincho);

            // Tentativas de ligar sem pré-condições:
            frota.forEach(Automovel::ligar); // CarroAutomatico e MotoPartidaPedal NÃO ligarão aqui


            // Satisfazendo pré-condições:
            carroAuto.pressionarFreio();
            motoPedal.puxarAcelerador();
            guincho.getVeiculoCarregado();

            // Agora liga
            frota.forEach(Automovel::ligar);

            System.out.println(carroAuto.informacaoAutomovel());// ligado
            System.out.println();
            System.out.println(motoPedal.informacaoMoto()); // ligada
            System.out.println();

            // Guincho carregando Carro e Moto:
            System.out.println("Guincho carregando Carro");
            guincho.carregar(carroAuto);
            System.out.println(guincho.getVeiculoCarregado());// mostra carga atual
            System.out.println(guincho.informacaoGuincho());   // ligado


            guincho.descarregar();
            guincho.carregar(motoPedal);
            System.out.println(guincho.informacaoGuincho());



            System.out.println(guincho.informacaoGuincho());

            System.out.println();
        }





    }

}

