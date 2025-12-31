package automovel;

import org.academiadb.automovel.CarroAutomatico;
import org.academiadb.automovel.Guincho;
import org.academiadb.automovel.MotoPartidaPedal;
import org.academiadb.automovel.TipoTransmissao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarroAutomaticoTest {

    @Test
    public void deveTestarCarroAutomatico() {
        CarroAutomatico carroAuto = new CarroAutomatico("Corolla", "Vemelho", 2023, "Gol", "ACK1D45", false, TipoTransmissao.AUTOMATICA);

        carroAuto.ligar();
        carroAuto.pressionarFreio();
        carroAuto.ligar();
        assertTrue(carroAuto.isLigado());

        assertNotNull(carroAuto.getModelo());
        assertNotNull(carroAuto.getCor());
        assertNotNull(carroAuto.getMarca());
        assertNotNull(carroAuto.getPlaca());
        assertEquals(2023, carroAuto.getAnoFabricacao());
        assertEquals(TipoTransmissao.AUTOMATICA, carroAuto.getTransmissao());


    }

    @Test
    public void MotoPartidaPedalTest() {
        MotoPartidaPedal motoPedal = new MotoPartidaPedal("CG 160", "Vermelha", 2021, "Honda", TipoTransmissao.MANUAL, "FRT4K17", false);

        motoPedal.ligar();
        motoPedal.puxarAcelerador();
        motoPedal.ligar();
        assertTrue(motoPedal.isLigado());

        assertNotNull(motoPedal.getModelo());
        assertNotNull(motoPedal.getCor());
        assertNotNull(motoPedal.getMarca());
        assertNotNull(motoPedal.getPlaca());
        assertEquals(2021, motoPedal.getAnoFabricacao());
        assertEquals(TipoTransmissao.MANUAL, motoPedal.getTransmissao());
    }

    @Test
    public void deveFalharAoLigarMotoPartidaPedalTest() {
        MotoPartidaPedal motoPedal = new MotoPartidaPedal("CG 160", "Vermelha", 2021, "Honda", TipoTransmissao.MANUAL, "FRT4K17", false);
        motoPedal.puxarAcelerador();
        motoPedal.ligar();
        assertTrue(motoPedal.isLigado());
    }

    @Test
    public void deveLigarGinunchoTest() {
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93", false);

        guincho.ligar();
        assertTrue(guincho.isLigado());

    }
    @Test
    public void deverLigarGinunchoFalhaTest() {
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93", false);
        guincho.ligar();
        guincho.desligar();

        assertFalse(guincho.isLigado());

    }
    @Test
    public void deveTestarGuinchoVeiculoCarregado(){
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93", false);

        guincho.getVeiculoCarregado();

        assertFalse(guincho.getVeiculoCarregado().isPresent());

    }

    @Test
    public void deveDescarregarVeiculoGuincho(){
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93", false);

        guincho.getVeiculoCarregado();

        assertFalse(guincho.getVeiculoCarregado().isPresent());

        CarroAutomatico carroAuto = new CarroAutomatico("Corolla", "Vemelho", 2023, "Gol", "ACK1D45", false, TipoTransmissao.AUTOMATICA);

        guincho.carregar(carroAuto);

        assertTrue(guincho.getVeiculoCarregado().isPresent());

        guincho.descarregar();

        assertFalse(guincho.getVeiculoCarregado().isPresent());

    }

    @Test
    public void deveDescarregarMotoGuincho(){
        Guincho guincho = new Guincho("Worker", "Volks", 2023, "Iveco", "ASD-5K93", false);

        guincho.getVeiculoCarregado();

        assertFalse(guincho.getVeiculoCarregado().isPresent());

        MotoPartidaPedal motoPedal = new MotoPartidaPedal("CG 160", "Vermelha", 2021, "Honda", TipoTransmissao.MANUAL, "FRT4K17", false);

        guincho.carregar(motoPedal);

        assertTrue(guincho.getVeiculoCarregado().isPresent());

        guincho.descarregar();

        assertFalse(guincho.getVeiculoCarregado().isPresent());

    }

    @Test
    public void deveTestarTipoTransmissao(){
        TipoTransmissao tipo1 = TipoTransmissao.AUTOMATICA;
        TipoTransmissao tipo2 = TipoTransmissao.MANUAL;

        assertEquals("AUTOMATICA", tipo1.name());
        assertEquals("MANUAL", tipo2.name());
    }



}
