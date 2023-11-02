package com.example.app;

import java.io.Serializable;

public class PersonalInformation implements Serializable {
    private String prenom;
    private String nom;
    private String email;
    private String phone;
    private String code;

    public PersonalInformation(String prenom, String nom, String email, String code, String phone) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.phone = phone;
        this.code = code;
    }

    public PersonalInformation(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

}
