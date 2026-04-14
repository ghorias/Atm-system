package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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



}
