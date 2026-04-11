package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Account {
    //fält
    //vi behöver ett unikt ID, kontonummer (string), ett saldo (Double eller BigDecimal)
    //sen en PIN-kod/hash
    @GeneratedValue
    @Id
    private long ID;

    private String kontonummer;

    private BigDecimal saldo;

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

    public BigDecimal getSaldo() {
        return saldo;
    }

    //setters


    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
