package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikeaLuodessa() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeKunRahaaTarpeeksi() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuKunRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void ottaminenPalauttaaTrueKunRahaaTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(5));
    }
    
    @Test
    public void ottaminenPalauttaaFalseKunRahaaEiTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void oikeaTulostus() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
