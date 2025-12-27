package automovel;

import org.academiadb.automovel.CarroAutomatico;
import org.academiadb.automovel.Guincho;
import org.academiadb.automovel.MotoPartidaPedal;
import org.academiadb.automovel.TipoTransmissao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarroAutomaticoTest {

    @Test
    @DisplayName("Deve testar carro automatico")
    public void deveTestarCarroAutomatico() {
        CarroAutomatico carroAuto = new CarroAutomatico("Corolla", "Vemelho", 2023, "Gol", "ACK1D45", false, TipoTransmissao.AUTOMATICA);

        carroAuto.ligar();
        carroAuto.pressionarFreio();
        carroAuto.ligar();
        assertTrue(carroAuto.isLigado());

        assertNotNull (carroAuto.getModelo());
        assertNotNull (carroAuto.getCor());
        assertNotNull (carroAuto.getMarca());
        assertNotNull (carroAuto.getPlaca());
        assertEquals (2023, carroAuto.getAnoFabricacao());
        assertEquals (TipoTransmissao.AUTOMATICA, carroAuto.getTransmissao());



    }

    @Test
    @DisplayName("Deve testar moto partida pedal")
    public void MotoPartidaPedalTest() {
        MotoPartidaPedal motoPedal = new MotoPartidaPedal("CG 160", "Vermelha", 2021, "Honda",TipoTransmissao.MANUAL, "FRT4K17", false);

        motoPedal.ligar();
        motoPedal.puxarAcelerador();
        motoPedal.ligar();
        assertTrue(motoPedal.isLigado());

        assertNotNull (motoPedal.getModelo());
        assertNotNull (motoPedal.getCor());
        assertNotNull (motoPedal.getMarca());
        assertNotNull (motoPedal.getPlaca());
        assertEquals (2021, motoPedal.getAnoFabricacao());
        assertEquals (TipoTransmissao.MANUAL, motoPedal.getTransmissao());
    }

    @Test
    public void ginunchoTest() {
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93",false);

        guincho.ligar();
        guincho.getVeiculoCarregado();
        guincho.ligar();
        assertTrue(guincho.isLigado());


        assertNotNull (guincho.getModelo());
        assertNotNull (guincho.getCor());
        assertNotNull (guincho.getMarca());
        assertNotNull (guincho.getPlaca());
        assertEquals (2023, guincho.getAnoFabricacao());


    }

}
