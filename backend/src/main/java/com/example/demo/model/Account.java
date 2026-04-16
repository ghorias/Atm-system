package com.example.demo.model;


import java.math.BigDecimal;


//Denna klass är själva objektet.

public class Account {
    //fält

    private String kontonummer;//affärsidentitet är kontonummer
    private BigDecimal saldo;


    //Annoteringar, för databas bör vi spara data i med denna klass
    //med @Entity kolla up det och @Id vad de gör. De ska sitta ovanför klassnamnet resp ID-variabler

    //Boilerplate
    //getters
    public String getKontonummer() {

        return kontonummer;
    }

    private BigDecimal getSaldo() {

        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }
}
