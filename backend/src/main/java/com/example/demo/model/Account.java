package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;


//Denna klass är själva objektet.
@Entity
public class Account {
    //fält
    //vi behöver ett unikt ID, kontonummer (string), ett saldo (Double eller BigDecimal)
    //sen en PIN-kod/hash
    @GeneratedValue
    @Id
    private long ID;//teknisk databasidentitet för id.

    @Column(unique = true) // så varje konto har ett unikt kontonummer.
    private String kontonummer;//affärsidentitet är kontonummer

    private BigDecimal saldo;

    private String lösenordHash;

    //Annoteringar, för databas bör vi spara data i med denna klass
    //med @Entity kolla up det och @Id vad de gör. De ska sitta ovanför klassnamnet resp ID-variabler

    //Boilerplate
    //getters
    public long getID() {
        return ID;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    private BigDecimal getSaldo() {
        return saldo;
    }

    public String getLösenordHash() {
        return lösenordHash;
    }

    //setters
    public void setLösenordHash(String lösenord) {
        this.lösenordHash = lösenord;
    }

    //KONSTRUKTOR, med denna kan vi göra ett nytt account smidigt.
    // Ex: Account Isak = new Account("12345678", new BigDecimal("1000.00"), "lösenord123");
    public Account(String kontonummer, BigDecimal saldo, String lösenord) {
        this.kontonummer = kontonummer;
        this.saldo = saldo;
        this.lösenordHash = lösenord;
    }

    public Account(){

    }


}
