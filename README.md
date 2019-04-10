# Pong

Oma versioni Pong pelistä, joka toimii Ohjelmistotekniikan harjoitustyönä.

## Dokumentaatio

[Käyttöohje](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

## Releaset

[Viikko 5](https://github.com/Jeeses313/ot-harjoitustyo/releases/)

## Komentorivitoiminnot 

### Testaus

Testit komennolla:

```
mvn test
```

Testikattavuusraportti komennolla:

```
mvn jacoco:report
```

Kattavuusraportti löytyy: _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento:

```
mvn package
```

Jar löytyy: _target_


### Checkstyle

Tiedostossa [checkstyle.xml](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/Pong/checkstyle.xml) määrittellyt tarkistukset komennolla:

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset löytyy: _target/site/checkstyle.html_