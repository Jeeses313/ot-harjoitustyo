package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(420);
    }

    @Test
    public void luodunKassapaatteenTiedotOikein() {
        assertEquals(true, (kassa.kassassaRahaa() == 100000) && (kassa.maukkaitaLounaitaMyyty() == 0) && (kassa.edullisiaLounaitaMyyty() == 0));
    }

    @Test
    public void edullisenOstoKateisellaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(260);
        assertEquals(20, vaihtoraha);
    }
    
    @Test
    public void edullisenOstoKateisellaKassaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(260);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKateisellaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(460);
        assertEquals(60, vaihtoraha);
    }
    
    @Test
    public void maukkaanOstoKateisellaKassaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(460);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    @Test
    public void edullisenOstoKateisellaKasvattaaLounaidenMaaraa() {
        kassa.syoEdullisesti(260);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaanOstoKateisellaKasvattaaLounaidenMaaraa() {
        kassa.syoMaukkaasti(420);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKateisellaKunRahaEiRiitaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        assertEquals(200, vaihtoraha);
    }
    
    @Test
    public void edullisenOstoKateisellaKunRahaEiRiitaKassaOikein() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKateisellaKunRahaEiRiitaVaihtorahaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(200);
        assertEquals(200, vaihtoraha);
    }
    
    @Test
    public void maukkaanOstoKateisellaKunRahaEiRiitaKassaOikein() {
        int vaihtoraha = kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoKateisellaKunRahaEiRiitaEiKasvataLounaidenMaaraa() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKateisellaKunRahaEiRiitaEiKasvataLounaidenMaaraa() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKortillaVahentaaKortinSaldoa() {
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(180, kortti.saldo());
    }
    
    @Test
    public void edullisenOstoKortillaPalauttaaTrue() {
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(true, onnistui);
    }
    
    @Test
    public void edullisenOstoKortillaEiMuutaKassaa() {
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKortillaVahentaaKortinSaldoa() {
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoKortillaPalauttaaTrue() {
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(true, onnistui);
    }
    
    @Test
    public void maukkaanOstoKortillaEiMuutaKassaa() {
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoKortillaKasvattaaLounaidenMaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaanOstoKortillaKasvattaaLounaidenMaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKortillaKunRahaEiRiitaPalauttaaFalse() {
        kortti.otaRahaa(400);
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(false, onnistui);
    }
    
    @Test
    public void edullisenOstoKortillaKunRahaEiRiitaSaldoOikein() {
        kortti.otaRahaa(400);
        boolean onnistui = kassa.syoEdullisesti(kortti);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void edullisenOstoKortillaKunRahaEiRiitaEiKasvataLounaidenMaaraa() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKortillaKunRahaEiRiitaKassaOikein() {
        kortti.otaRahaa(400);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKortillaKunRahaEiRiitaPalauttaaFalse() {
        kortti.otaRahaa(400);
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(false, onnistui);
    }
    
    @Test
    public void maukkaanOstoKortillaKunRahaEiRiitaSaldoOikein() {
        kortti.otaRahaa(400);
        boolean onnistui = kassa.syoMaukkaasti(kortti);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoKortillaKunRahaEiRiitaEiKasvataLounaidenMaaraa() {
        kortti.otaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKortillaKunRahaEiRiitaKassaOikein() {
        kortti.otaRahaa(400);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void negatiivinenLatausEiMuutaKortinSaldoa() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(420, kortti.saldo());
    }
    
    @Test
    public void negatiivinenLatausEiMuutaKassaa() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausKasvattaaKortinSaldoaOikein() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(430, kortti.saldo());
    }
    
    @Test
    public void rahanLatausKasvattaaKassaaOikein() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(100010, kassa.kassassaRahaa());
    }
}
