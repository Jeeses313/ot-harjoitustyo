## Arkkitehtuuri

### Rakenne

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/pakkauskaavio.png "Pakkauskaavio")

Ohjelman toiminnallisuus jakautuu neljään paukkaukseen: _tools_, _dao_, _domain_ ja _ui_, jotka ovat ohjelman pohjapakkauksessa _pong_, jossa on myös ohjelman käynnistävä luokka _Pong_. Vaikka 
kaaviossa _Pong_-luokalla onkin yhteys _dao_ ja _tools_ pakkauksiin, se ei itse tee niillä mitään, vaan pitää niiden sisällä olevia luokkia muistissa.

* Pakkauksessa _ui_ on ohjelman käyttöliittymän toiminta.  
* Pakkauksessa  _da_ on ohjelman pysyväistallennuksen toiminta.  
* Pakkauksessa _tools_ on työkaluja eri pakkauksissa olevien luokkien toiminnalle.  
* Pakkauksessa domain on sovelluslogiikka ja se on jaettu kolmeen pakkaukseen: _games_, _actors_ ja _player_.   
 * Pakkauksessa _games_ on ohjelman pelien toiminta (Tavallinen peli ja pallottelu peli).  
 * Pakkauksessa _actors_ on pelissä näkyvien olioiden toiminta.  
 * Pakkauksess _player_ on pelaajiin liittyvä toiminta.  

### Luokkakaavio

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/luokkakaavio.png "Luokkakaavio")

### Käyttöliittymä

Käyttöliittymä muodostuu _ui_ pakkauksen luokista:  
* _Options_  
* _Menu_  
* _HighscoreScreen_  
* _NormalGameScreen_  
* _RallyGameScreen_  
jotka toteuttavat rajapinnan _Screen_. Kaikilla luokilla on oma Scene-olio ja ne ovat yksikerrallaan pääluokan _Pong_ 
stageen sijoitettuina, eli näkyvänä. Näkymää vaihtaessa luodaan uusi _Screen_-rajapintaa toteuttava luokka, jonka start-metodia kutsutaan, jolloin näkymä vaihdetaan stageen 
ja peleissä käynnistetään myös AnimationTimer.

Käyttöliittymä on eristetty sovelluslogiikasta, mutta varsinkin _NormalGameScreen_ ja _RallyGameScreen_ luokissa olevat AnimationTimer-oliot ovat välttämättömiä 
pelien toiminnalle, koska ne pitävä pelin käynnissä ja suorittavat sovelluslogiikan toiminnot oikeassa järjestyksessä.

### Sovelluslogiikka

Sovelluslogiikka muodostuu _domain_ pakkausessa olevista:  
* _actors_ pakkauksen luokista:  
 * _Ball_  
 * _Bat_  
 * _Powerup_  
 * _Wall_  
 ja rajapinnnasta _Collisionable_, jota kaikki pakkauksen luokat toteuttavat.  
* _games_ pakkauksen luokista:  
 * _NormalGame_  
 * _RallyGame_  
* _player_ pakkauksen luokista:  
 * _Player_  
 * _Human_  
 * _Ai_ 

_games_ pakkauksen luokat hallitsevat kokonaisuutta, johon muu _domain_ pakkauksen sisältö kuuluu, vaikka eivät osaakkaan tehdä 
kaikkea oikeassa järjestyksessä, koska järjestys on hoidettu käyttöliittymän puolella. _NormalGame_ ja _RallyGame_ ohjaavat _Ball_, _Wall_ ja _Powerup_ olioita ja 
_player_ pakkausen olioita, jotka taas ohjaavat omia _Bat_ oliotaan saamiensa tietojen perusteella.

Jokaisella _NormalGameScreen_ ja _RallyGameScreen_:ien AnimationTimer-olion suoritus kierroksella toimitaan samassa järjestyksessä kutsumalla _NormalGame_ ja _RallyGame_ olioiden metodeja:  
1. Jos peli ei ole paussilla liikutetaan pelaajien mailoja käyttöliittymän puolen kuuntelijalta saatujen näppäinten painallusten mukaan. Ai liikuttaa mailaa pallon sijainnin mukaan.  
2. Tarkistetaan onko pause- tai menu-näppäimiä painettu ja onko peli tällä hetkellä paussilla sovelluslogiikan puolella ja toimitaan palautetun arvon mukaisesti käyttöliittymän puolella. 
Mahdollisia toimintoja on neljä:  
 1. Ei tehdä mitään.  
 2. Laitetaan paussiteksti näkyville.  
 3. Poistetaan paussiteksti näkyvistä.  
 4. Siirrytään menuun.  
3. Tarkistetaan taphtuuko törmäyksiä, jossa samalla oli suorittavat törmäyksessä tapahtuvat toiminnot, kuten pallon liikkeen kääntyminen, pallon nopeentuminen ja lisävoiman aktivoituminen.  
4. Tarkistetaan onko pallo maalissa sovelluslogiikan puolella ja toimitaan palautetun arvon mukaan käyttöliittymän puolella. Mahdollisia tapahtumia on kaksi:  
 1. Näytetään teksti, joka kertoo toisen voittaneen. Ai:n voittaessa ja pallottelussa, että pelaaja hävisi.
 2. Näytetään pelin alussa oleva teksti pelin aloittamiselle.  
5. Hallitaan lisävoimien toimintoja, jos lisävoimat ovat päällä, eli uuden lisävoiman tekeminen, lisävoiman vaikutuksen loppuminen ja lisävoiman katoaminen.  
6. Jos peli ei ole paussilla, pallon liikuttaminen.
7. Muutosten piirtäminen ruudulle.

### Tietojen pysyväistallennus

Pakkauksen _dao_ luokat _ConfigurationDao_ ja _HighscoresDao_ huolehtivat tietojen tallettamisesta tiedotoihin. 
_ConfigurationDao_ tallettaa tiedostoon _config.properties_ ja _HighscoresDao_ tietokantaan _highscores.mv.db_, mutta niille voi antaa 
toisenkin tiedoston nimen, kuten luokkien testeissä onkin tehty.

### Teidostot

Ohjelma talleetta asetusten tiedot _config.properties_ tiedostoon ja pistetiedot _highscores.mv.db_ tietokantaan.

Tiedostojen nimet on määritelty ohjelman pääluokassa _Pong_, kun _dao_ pakkausen luokkia kutsutaan ensimmäisen kerran, minkä takia nimet voi vaihtaa muuttamalla 
pääluokan koodia.

Asetusten formaatti on `avain=arvo` eikä järjestyksellä ole väliä. Oletus asetukset ovat:
```  
speedUp=1  
menu=M  
Player2_colour=0x000000ff  
Player1_Up=W  
endingpoint=5  
Player1_colour=0x000000ff  
Player1_Down=S  
difficulty=1  
BatSpeed=4  
Ball_colour=0x000000ff  
powerups=0  
Player2_Down=Down  
BallSpeed=8  
Player2_Up=Up  
pause=P  
```  
Virheelliset arvot käsitellään niin, että asetus muutetaan oletusarvoonsa.  

Pistetiedot on talletettu yhteen tietokantatauluun, jolla on kolme saraketta: id, nimi ja pisteet. Maksimissaan tietokannassa säilytetään kuutta erillistä 
pistetietoa, koska _HighscoreScreen_ näyttää viisi tai neljä, jos on tehty uusi ennätys, ja ruudulle siirtyessä ylimääräiset poistetaan, ettei tietokannan koko kasva liikaa.

### Päätoiminnallisuudet

#### Asetusten muuttaminen

Esim. Kun käyttäjä asetusruudussa "Options" muuttaa vaikeustason vaikeaksi ja pause näppäimen näppäimeksi 'O':

![alt text](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssikaavio_asetusten_muutos.png "Asetusten muutos sekvenssikaavio")

Kun käyttäjä klikkaa hardDifficultyButtonia, tapahtumakäsittelijä kutsuu asetusten tietoja säilyttävän ja käsittelevän Configurations-luokan metodia, jolla 
tiedot muutetaan Configurations-luokan sisällä. Metodista kutsutaan myös ConfigurationsDao-luokan metodia, jolla tiedot muutetaan myös asetuksia säilyttävässä tiedostossa.

Kun käyttäjä klikkaa pauseButtonia, Options ruutu alkaa kuunnella näppäimiä siihen asti, että käyttäjä painaa ensimmäisen kerran jotain näppäintä. 
Kun näppäintä painetaan, toimitaan samoin kuin vaikeustasoa muuttaessa, eli muutetaan tiedot Configurations-luokan sisällä ja asetuksia säilyttävässä tiedostossa. 
Lopuksi näppäinten kuuntelu lopetetaan.

Muilla asetuksilla toimitaan samoin.