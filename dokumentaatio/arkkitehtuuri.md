## Arkkitehtuuri

### Luokkakaavio

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/luokkakaavio.png "Luokkakaavio")

### Sekvenssikaavio

#### Asetusten muuttaminen

Esim. Kun käyttäjä asetusruudussa "Options" muuttaa vaikeustason vaikeaksi ja pause näppäimen näppäimeksi 'O':

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssikaavio_asetusten_muutos.png "Asetusten muutos sekvenssikaavio")

Kun käyttäjä klikkaa hardDifficultyButtonia, tapahtumakäsittelijä kutsuu asetusten tietoja säilyttävän ja käsittelevän Configurations-luokan metodia, jolla 
tiedot muutetaan Configurations-luokan sisällä. Metodista kutsutaan myös ConfigurationsDao-luokan metodia, jolla tiedot muutetaan myös asetuksia säilyttävässä tiedostossa.

Kun käyttäjä klikkaa pauseButtonia, Options ruutu alkaa kuunnella näppäimiä siihen asti, että käyttäjä painaa ensimmäisen kerran jotain näppäintä. 
Kun näppäintä painetaan, toimitaan samoin kuin vaikeustasoa muuttaessa, eli muutetaan tiedot Configurations-luokan sisällä ja asetuksia säilyttävässä tiedostossa. 
Lopuksi näppäinten kuuntelu lopetetaan.

Muilla asetuksilla toimitaan samoin.