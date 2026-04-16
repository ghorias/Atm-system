package com.example.demo.model;

public class Admin {

    private String firstName;
    private String lastName;
    private String Mail;
    private String Adress;
    private long personnummer;
    private long AdminID; //primarykey

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
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

    public long getAdminID() {
        return AdminID;
    }

    public void setAdminID(long adminID) {
        AdminID = adminID;
    }

    public Admin(String firstName, String lastName, String mail, String adress, long personnummer, long adminID) {
        firstName = firstName;
        lastName = lastName;
        Mail = mail;
        Adress = adress;
        this.personnummer = personnummer;
        AdminID = adminID;
    }

    public Admin(){

    }
}
