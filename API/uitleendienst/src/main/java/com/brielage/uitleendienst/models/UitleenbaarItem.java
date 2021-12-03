package com.brielage.uitleendienst.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable (tableName = "uitleenbaarItem")
public class UitleenbaarItem {
    private String id;
    private String naam;
    private int    eenheid;
    private float  prijs;
    private String periode;
    private String categorieId;

    public UitleenbaarItem () {}

    public UitleenbaarItem (
            String naam,
            int eenheid,
            float prijs,
            String periode,
            String categorieId) {
        this.naam        = naam;
        this.eenheid     = eenheid;
        this.prijs       = prijs;
        this.periode     = periode;
        this.categorieId = categorieId;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId () {
        return id;
    }

    @DynamoDBAttribute
    public void setId (String id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public String getNaam () {
        return naam;
    }

    @DynamoDBAttribute
    public void setNaam (String naam) {
        this.naam = naam;
    }

    @DynamoDBAttribute
    public int getEenheid () {
        return eenheid;
    }

    @DynamoDBAttribute
    public void setEenheid (int eenheid) {
        this.eenheid = eenheid;
    }

    @DynamoDBAttribute
    public float getPrijs () {
        return prijs;
    }

    @DynamoDBAttribute
    public void setPrijs (float prijs) {
        this.prijs = prijs;
    }

    @DynamoDBAttribute
    public String getPeriode () {
        return periode;
    }

    @DynamoDBAttribute
    public void setPeriode (String periode) {
        this.periode = periode;
    }

    @DynamoDBAttribute
    public String getCategorieId () {
        return categorieId;
    }

    @DynamoDBAttribute
    public void setCategorieId (String categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public String toString () {
        return "UitleenbaarItem{" +
                "id='" + id + '\'' +
                ", naam='" + naam + '\'' +
                ", eenheid=" + eenheid +
                ", prijs=" + prijs +
                ", periode='" + periode + '\'' +
                ", categorieId='" + categorieId + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof UitleenbaarItem ui) {
            return this.getId()
                       .equals(ui.getId());
        }
        return false;
    }
}
