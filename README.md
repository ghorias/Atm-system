# ATM-system: Secure Concurrency Banking

## Summering
Projektet går ut på att bygga ett komplett, säkert och användarvänligt ATM-system (Bankomat). Systemet ska simulera en verklig bankmiljö där användare kan logga in, hantera sitt saldo och utföra transaktioner. Vi bygger en modern "fullstack"-applikation med en robust backend i **Java (Spring Boot)**, en interaktiv frontend i **React**, och en persistent databas i **SQLite**. Stort fokus ligger på logik, säkerhet (lösenordshashing) och att hantera samtidiga transaktioner (concurrency).

## MVP (Minimum Viable Product)
Vår MVP fokuserar på kärnlogiken för en säker bankomat och består av:
* **Inloggning:** Simpel enkel inloggning utan lösenord.
* **Transaktioner:** Grundläggande logik för att se saldo, sätta in pengar och ta ut pengar (med validering mot negativt saldo).
* **Persistens:** Hårdkodad Data.
* **Fullstack-koppling:** En enkel React-frontend som kommunicerar med en Spring Boot-backend via ett REST API.
* **Trådsäkerhet:** Implementering av grundläggande transaktionshantering (`@Transactional`) för att säkerställa dataintegritet.

---

## Teknisk Översikt

### Backend (Java / Spring Boot)
* **Modeller (Entities):** Klasser som representerar ett `Account` (ID, kontonummer, saldo, hashat lösenord) och en `Transaction` (datum, belopp, typ).
* **Logik (Services):** Funktioner för att räkna ut saldo, validera att man inte tar ut mer än man har, samt hantera admin-rättigheter.
* **API (Controllers):** De “portar” som frontenden pratar med, t.ex. `/api/login` och `/api/withdraw`.
* **Säkerhet:** Implementering av **BCrypt** för att kryptera PIN-koder innan de sparas i databasen.

### Frontend (JavaScript / React)
**Komponenter:**
* **Login:** Inmatning av kontonummer och PIN.
* **Dashboard:** Överblick av saldo och snabbval.
* **TransactionForm:** Input-fält för att sätta in eller ta ut pengar.
* **AdminView:** En separat vy för att se alla konton (endast för administratörer).

**State Management:**
* Systemet håller koll på om användaren är inloggad och vad det aktuella saldot är direkt i webbläsaren för en smidig användarupplevelse.

### Databas (SQLite)
Här sparas all data permanent för att undvika dataförlust vid omstart:
* **Accounts Table:** Sparar alla användare och deras profiler.
* **Transactions Table:** Sparar historik över alla insättningar och uttag (viktigt för transparens och loggning).

---

## Teknisk Specifikation
* **Språk:** Java, JavaScript (JSX)
* **Frameworks:** Spring Boot, React
* **Verktyg:** Maven, SQLite, Git/GitHub
* **Säkerhet:** BCrypt Hashing



## Förslag på "Twists" Vad tycker ni?
Vi har tagit fram fyra möjliga utökningar för att ge projektet mer teknisk tyngd. Vilken av dessa anser du är mest relevant att prioritera efter att MVP:n är klar?

1. **The "Social" ATM (Concurrency Focus)**
   Hantera delade konton där två användare kan dra pengar samtidigt (fokus på Race Conditions och låsning i Java).
2. **The "Cyber" Twist (Intrusion Detection)**
   Implementera Rate Limiting (låsa konto vid 3 felaktiga försök) och flaggning av misstänkt aktivitet i en Admin-vy.
3. **The "FinTech" Twist (External API Integration)**
   Integrera ett externt API för att hämta live-växelkurser så att användaren kan växla mellan SEK, EUR och USD.
4. **The "UX-Security" Twist (Simulated 2FA)**
   Större uttag kräver ett extra godkännande i en simulerad "mobil-app" i React innan transaktionen godkänns.