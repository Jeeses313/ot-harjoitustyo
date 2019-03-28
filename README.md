# Pong

Oma versioni Pong pelistä, joka toimii Ohjelmistotekniikan harjoitustyönä.

## Dokumentaatio

[Käyttöohje](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Jeeses313/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

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
