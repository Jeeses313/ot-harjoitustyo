## Testausdokumentti

Ohjelmaa on testattu JUnitilla yksikkö- ja integraatiotesteillä ja manuaalisesti.

### Yksikkö- ja integraatiotestaus

#### Sovelluslogiikka

Sovelluslogiikka, eli _domain_ pakkauksen sisältöä, testaavat:  
- [actortests](https://github.com/Jeeses313/ot-harjoitustyo/tree/master/Pong/src/test/java/actortests) pakkauksen luokat  
- [playertests](https://github.com/Jeeses313/ot-harjoitustyo/tree/master/Pong/src/test/java/playertests) pakkauksen luokat  
- [gametests](https://github.com/Jeeses313/ot-harjoitustyo/tree/master/Pong/src/test/java/gametests) pakkauksen luokat  

Luokkiin sisältyy yksikkö- ja integraatiotestejä, koska monet luokkien toiminnot vaativat toisia luokkia toimiakseen. 

#### DAO-luokat

DAO-luokkien testaus tapahtuu testiluokissa [HighscoreDaoTest](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/Pong/src/test/java/daotests/HigscoresDaoTest.java) ja [ConfigurationTest](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/Pong/src/test/java/tooltests/ConfigurationTest.java), 
vaikka _ConfigurationTest_ testaakin myös [Configurations](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/Pong/src/main/java/pong/tools/Configurations.java) luokkaa. 
Testaus on toteutettu antaen luokille testaukseen käytettävien tiedostojen nimet, jotka testatessa luodaan, jos niitä ei ole, ja alustetaan luokissa olevalla toiminnalla.

#### Testikattavuus

Käyttöliittymää ja siihen komponentteja luovaa [ComponentCreator](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/Pong/src/main/java/pong/tools/ComponentCreator.java) luokkaa lukuunottamatta sovelluksen testauksen 
rivikattavuus on 97% ja haarautuvuuskattavuus 88%.

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/testikattavuuskuva.png "Testikattavuus")

Testaamattomia tilanteita ovat esimerkiksi tiedostoihin/tiedostoja kirjoittaessa/lukiessa tapahtuvat virheet, 
kun tiedostoon/tiedosto on jo aiemmin saatu kirjoitettua/luettua, 
ja satunnaisuuteen liittyvät testit, kun kaikkia haaroja ei suoriteta satunnaislukujen takia. 

### Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti.

#### Asennus

Sovellus on asennettu [Asennusohjeen](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) mukaisesti Windows 10-ympäristöön, lataamalla tiedoston 
ohjeista löytyvällä linkillä, siirtämällä tiedoston kansioon ja käynnistämällä sen.

Sovellusta on testattu, kun kansiossa on sen käyttämät tiedostot ja kun niitä ei ole, jolloin ne on luotu.

#### Konfigurointi

Sovellusta on testattu muokkaamalla käyttöohjeessa kerrotulla tavalla _config.properties_-tiedostoa, niin järkevällä kuin ei 
järkevällä syötteellä. Vääränlaiset ja puuttuvat konfiguraatiot korjataan käyttöohjeessa kerrotulla tavalla, eli asettamalla ne oletusarvoihinsa.

#### Toiminnalisuudet

Kaikki [määrittelydokumentin](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeen listaamat 
toiminnallisuudet on käyty läpi ja kohdissa,, joissa pyydetään syötettä, on annettu myös virheellisiä arvoja.

### Sovellukseen jääneet laatuongelmat

- Sovellus ei tee mitään, jos tiedostoon/tiedostoa kirjoittaessa/lukiessa taphtuu virhe tiedoston ensimmäisen kirjoitus-/lukukerran jälkeen.

- Pelissä ei voi käyttää kaikkia näppäimiä, koska KeyCode ei tunnista esim. ääkkösiä.

- Pallon ja mailan välisessä törmäyksessä pallo voi jäädä mailan sisään hetkeksi. Tapahtuu yleensä, jos pallo osuu mailan ylä- tai 
alareunaan. 