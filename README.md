# TechGearHub - E-commerce Backend 🚀

TechGearHub è il backend di un'applicazione e-commerce per la vendita di componenti hardware e periferiche PC di fascia alta. Il progetto è sviluppato in Java utilizzando **Spring Boot** e **PostgreSQL** come database relazionale, strutturato seguendo le migliori pratiche dell'architettura enterprise (Layered Architecture) e il pattern DTO (Data Transfer Object).

Il backend è predisposto per essere integrato nativamente con un frontend moderno (es. Next.js).

---

## 🛠️ Tech Stack & Architettura

* **Linguaggio:** Java 17
* **Framework:** Spring Boot 3.x / 4.x
* **Persistenza Dati:** Spring Data JPA / Hibernate
* **Database:** PostgreSQL 18
* **Utility:** Lombok (per un codice pulito e senza boilerplate), Java Records (per i DTO)

### Struttura del Progetto
L'applicazione segue una rigorosa separazione delle competenze (*Separation of Concerns*):
* `model`: Entità JPA mappate direttamente sulle tabelle del database.
* `dto`: Record Java immutabili per il trasferimento dei dati verso l'esterno, evitando l'esposizione diretta del database.
* `repository`: Interfacce che estendono `JpaRepository` per la gestione delle query SQL tramite Spring Data.
* `config`: Classi di configurazione e popolamento iniziale del database.

---

## ⚙️ Prerequisiti

Prima di avviare l'applicazione localmente, assicurati di avere installato:
* **Java JDK 17** o superiore
* **PostgreSQL** (attivo e in esecuzione sulla porta standard `5432`)
* Un IDE compatibile (Eclipse / Spring Tool Suite, IntelliJ IDEA, VS Code)

---

## 🚀 Configurazione e Avvio

### 1. Preparazione del Database
Accedi a pgAdmin (o alla CLI di Postgres) e crea un database vuoto con il nome impostato nell'applicazione:
```sql
CREATE DATABASE techgear_db;# TechGearHub
