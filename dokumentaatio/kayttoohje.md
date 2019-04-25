## Asennus- ja käyttöohje

### Asennusohje

#### Ennen kuin aloitat

* Ohjelma vaatii [Javan](https://www.java.com/en/) toimiakseen.

#### Asennus

1. Lataa uusin versio [tästä](https://github.com/Jeeses313/ot-harjoitustyo/releases/latest/download/Pong.jar).

2. Siirrä ladattu .jar-tiedosto haluamaasi hakemistoon.

3. Asennus on nyt valmis. Käynnistäessäsi ohjelman ensimmäistä kertaa, hakemistoon luodaan config.properties tiedosto ja käydessäsi ensimmäistä kertaa _Highscores_ ruudussa, hakemistoon luodaan tiedostot highscore.mv.db ja highscore.trace.db.

### Käyttöohje

#### Ohjaus

* Kaikki pelissä käytetyt näppäimet näkyvät ja ovat muutettavissa _Options_ ruuudussa.

* Pelaajilla on kaksi näppäintä ohjausta varten. Toisella liikutetaan mailaa ylös ja toisella alas.

* Pelin saa paussille sille määritellyyllä näppäimellä.

* Pelin saa keskeytettyä laittamalla pelin paussille ja painamalla menu-näppäintä.

#### Pelaaminen

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/valikkokuva.png "Valikko")


##### Kaikki pelit

* Pelin alussa painetaan pause-näppäintä pelin aloittamiseksi.

* Pallo lähtee liikkumaan pelin alussa satunnaiseen suuntaan.

* Pallon osuessa mailaan tai seinään (pallottelu) sen vaakasuuntainen liike kääntyy vastakkaiseksi.

* Jos pelaaja liikuttaa mailaa pallon pystysuuntaista liikettä vastakkaiseen suuntaan osuessaan siihen, 
pallon pystysuuntainen liike kääntyy myös. 

* Pelin loputtua pause- ja menu-näppäimet siirtävät pois peliruudusta.

##### Tavallinen peli (_1 Player_ ja _2 Player_)

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/pelikuva.png "Tavallinen peli")

* Pallon mennessä maaliin, maalin vastakkaisella puolella oleva pelaaja saa pisteen.

* Jos maali ei saavuta voittoon vaadittavaa pistemäärää, kenttä palaa aloitus tilanteeseen ja peli jatkuu pause-näppäintä painamalla.

* Jos maali saavuttaa voittoon vaadittavan pistemäärän, peli loppuu ja pause-näppäintä painamalla pääsee takaisin päävalikkoon.

* Pelin asetuksia voi muuttaa.

* Jos lisävoimat ovat käytössä, pelikentälle ilmestyy erivärisiä kallellaan olevia neliöitä, jotka katoavat, jos niihin osutaan tai jos niihin ei osuta 10s aikana. 
Siniset ja punaiset lisävoimat vaikuttavat pelaajaan, josta pallon liikkuu poispäin, ja vihreät vaikuttavat palloon. Lisävoiman vaikutuksen kesto on 15s. Vihreät lisävoimat voivat nopeentaa tai hidastaa palloa. 
Punaiset lisävoimat voivat joko hidastaa tai pienentää mailaa. Siniset lisävoimat voivat joko kasvattaa tai nopeentaa mailaa.

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/lisavoimakuva.png "Lisävoima")

##### Pallottelupeli (_Rally game_)

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/pallottelukuva.png "Pallottelupeli")

* Seinään osuessa pelaaja saa pisteen ja pallon nopeus kasvaa.

* Asetuksia ei voi muuttaa.

* Peli loppuu, kun pallo ensimmäisen kerran menee pelaajan puoleiseen maaliin.

* Pelin loputtua pause-näppäin vie pelaajaan _Highscores_ ruutuun, josta näkee piste-ennätykset. Jos pisteitä sai pelissä tarpeeksi 
Voi uudet pisteet kirjata muistiin kirjoittamalla pisteiden kohdalle nimen ja painamalla _Submit_ nappia. _Reset scores_ napilla voi palauttaa pisteet oletuksiinsa.

* Pelin loputtua menu-näppäimellä pääsee takaisin päävalikkoon.

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/highscorekuva1.png "Pisteiden kirjaaminen")

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/highscorekuva2.png "Pisteiden näkyminen")


#### Asetukset (_Options_)

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/asetuskuva.png "Asetukset")

* _Options_ ruudussa voi muuttaa näppäimiä painamalla nappia, jossa lukee tämän hetkisen näppäimen nimi. Tämän jälkeen nimi katoaa ja painamalla jotain näppäintä 
nappiin tulee painetun näppäimen nimi ja näppäin on vaihdettu.

* Pelaajien ja pallon väriä voi vaihtaa painamalla väriruutua kohteen nimen vieressä. Tällöin ilmestyy värivalikko, josta väriä painamalla väri muutetaan. On myös 
mahdollista painaa kohtaa _Custom Color_, jolloin saa enemmän muokkaus vaihtoehtoja.

* Muita pelin asetuksia, jotka vaikuttavan vain tavallisessa pelissä (_1 Player_ ja _2 Player_): mailan nopeutta, pallon nopeutta, pallon nopeutumista, lopetuspistemäärää, 
lisävoimien käytössä olemista ja tekoälyn vaikeustasoa, voi muuttaa nappia painamalla. Kannatta huomioida, että tekoäly on tehty normaaleja asetuksia miettien.

* Lukuja käsitteleviä asetuksia (nopeus, pisteet, yms.) voi muuttaa vapaammin kirjoittamalla _config.properties_ tiedostoon. Esim. `endingpoint=313`. Jos jokin muutos aiheuttaisi virheen, asetus muutetaan takaisin oletukseen automaattisesti.

* _Reset to default_ nappilla voi palauttaa asetukset oletuksiinsa.
