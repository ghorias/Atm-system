package com.example.demo.model;

public class User {

    //fält
    private String FirstName;
    private String LastName;
    private String Mail;
    private String Adress;
    private long personnummer; //primary key??

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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
        FirstName = firstName;
        LastName = lastName;
        Mail = mail;
        Adress = adress;
        this.personnummer = personnummer;
    }

    public User(){
        
    }
}
