# **Här är förklaringen av arktitektur av vårt projekt**

## Begrepp 

### Maven
Maven är ett build-verktyg för Java som automatiserar hur ett projekt byggs, testas och paketeras, samtidigt som det hanterar alla externa bibliotek (dependencies) som projektet behöver. Genom konfigurationsfilen pom.xml beskriver du projektets struktur och krav, och Maven ser till att ladda ner rätt beroenden, kompilera koden, köra tester och skapa en körbar fil (t.ex. en .jar). Detta gör utvecklingen mer effektiv, konsekvent och reproducerbar eftersom alla som arbetar med projektet använder samma uppsättning verktyg och versioner.
### Maven Wrapper
**Maven wrapper** är ett verktyg som låter ett projekt köra Maven utan att Maven behöver vara installerat globalt på datorn
*Wrapper* generellt är ett lager runt ett verktyg eller system som, förenklar användning, standardiserar beteende och döljer komplexitet.
### Database
en databas är ett system för att lagra och hantera data på ett strukturerat sätt.
### API
Ett API är ett gränssnitt som låter olika system kommunicera med varandra. Ofta via HTTP i from av REST.
### Framework
Ett framework är ett verktyg som ger struktur och färdiga lösningar för utveckliong. Exempel är Spring Boot
### paket
paket är en logisk gruppering av relaterade klasser och filer. Det används för att organisera kod,
undvika namnkonflikter och skapa en tydlig struktur i projekt

## projektet
### model-paketet 
model-paketet är en ritning eller ett formulär. Den är beskrivningen av ett **ting**
*Account.java* talar om vad ett konto är. Den säger att ett konto har t.e.x ett kontonummer och ett saldo
så konto1: (namn: Albert, saldo: 10003kr)

### service-paketet
paket för logik. Den innehåller hjärnan.
AccountService.java skriver reglerna, den använder information från Account för att göra saker som withdraw t.e.x eller visa saldo
Så när en person vill ta ut pengar sker detta i serivce:
1. hämtar ditt Account (modellen)
2. kollar om det finns 100kr på saldot (logik)
3. räknar ut det nya saldot
4. sparar det.

