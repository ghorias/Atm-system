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
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        this.Mail = mail;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        this.Adress = adress;
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
        this.AdminID = adminID;
    }

    public Admin(String firstName, String lastName, String mail, String adress, long personnummer, long adminID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Mail = mail;
        this.Adress = adress;
        this.personnummer = personnummer;
        this.AdminID = adminID;
    }

    public Admin(){

    }
}
