package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstr() {
        varasto = new Varasto(-1);
        varasto = new Varasto(0);
        varasto = new Varasto(1,1);
        varasto = new Varasto(1,2);
        varasto = new Varasto(-1,2);
        varasto = new Varasto(-1,-1);
        varasto.toString();
    }
    @Test
    public void eiVoiOttaaLiikaa() {
        double otos = varasto.getSaldo() + 1;
        varasto.otaVarastosta(otos);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLaittaaLiikaa() {
        double rajanylitys = varasto.getTilavuus() +1;
        varasto.lisaaVarastoon(rajanylitys);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void eiVoiOttaaNegatiivisiaMääriä() {
        double saldo = varasto.getSaldo();
        varasto.otaVarastosta(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    } 
    @Test
    public void eiVoiLaittaaNegatiiviaMääriä() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-1);
        assertEquals(saldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void paljonkoMahtuuToimiiKunEiTilaa() {
        varasto.lisaaVarastoon(varasto.getTilavuus());
        assertEquals(0.0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void paljonkoMahtuuToimiiKunVarastoOnTyhjä() {
        varasto.otaVarastosta(varasto.getTilavuus());
        assertEquals(varasto.paljonkoMahtuu(), varasto.getTilavuus(), vertailuTarkkuus);
    }
}