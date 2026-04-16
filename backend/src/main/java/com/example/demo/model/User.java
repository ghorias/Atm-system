package com.example.demo.model;

public class User {

    //fält
    private String firstName;
    private String lastName;
    private String Mail;
    private String Adress;
    private long personnummer; //primary key??
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public long getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(long personnummer) {
        this.personnummer = personnummer;
    }

    public User(String firstName, String lastName, String mail, String adress, long personnummer) {
        firstName = firstName;
        lastName = lastName;
        Mail = mail;
        Adress = adress;
        this.personnummer = personnummer;
    }

    public User(){
        
    }
}
