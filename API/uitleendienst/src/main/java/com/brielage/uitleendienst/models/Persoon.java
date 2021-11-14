package com.brielage.uitleendienst.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable (tableName = "persoon")
public class Persoon {
    private String id;
    private String naam;
    private String adres;
    private String telefoon;
    private String email;
    private String opmerking;

    public Persoon() {
    }

    public Persoon(String naam, String adres, String telefoon, String email, String opmerking) {
        this.naam = naam;
        this.adres = adres;
        this.telefoon = telefoon;
        this.email = email;
        this.opmerking = opmerking;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getNaam() {
        return naam;
    }

    @DynamoDBAttribute
    public void setNaam(String naam) {
        this.naam = naam;
    }

    @DynamoDBAttribute
    public String getAdres() {
        return adres;
    }

    @DynamoDBAttribute
    public void setAdres(String adres) {
        this.adres = adres;
    }

    @DynamoDBAttribute
    public String getTelefoon() {
        return telefoon;
    }

    @DynamoDBAttribute
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute
    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute
    public String getOpmerking() {
        return opmerking;
    }

    @DynamoDBAttribute
    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }
}
