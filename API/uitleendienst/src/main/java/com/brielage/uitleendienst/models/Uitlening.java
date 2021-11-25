package com.brielage.uitleendienst.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable (tableName = "uitlening")
public class Uitlening {
    private String      id;
    private Organisatie organisatie;
    private Magazijn    magazijn;
    private String        start;
    private String        eind;
    private String        teruggebrachtOp;
    private String      opmerking;

    public Uitlening () {}

    public Uitlening (
            Organisatie organisatie,
            Magazijn magazijn,
            String start,
            String eind,
            String teruggebrachtOp,
            String opmerking) {
        this.organisatie     = organisatie;
        this.magazijn        = magazijn;
        this.start           = start;
        this.eind            = eind;
        this.teruggebrachtOp = teruggebrachtOp;
        this.opmerking       = opmerking;
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
    public Organisatie getOrganisatie () {
        return organisatie;
    }

    @DynamoDBAttribute
    public void setOrganisatie (Organisatie organisatie) {
        this.organisatie = organisatie;
    }

    @DynamoDBAttribute
    public Magazijn getMagazijn () {
        return magazijn;
    }

    @DynamoDBAttribute
    public void setMagazijn (Magazijn magazijn) {
        this.magazijn = magazijn;
    }

    @DynamoDBAttribute
    public String getStart () {
        return start;
    }

    @DynamoDBAttribute
    public void setStart (String start) {
        this.start = start;
    }

    @DynamoDBAttribute
    public String getEind () {
        return eind;
    }

    @DynamoDBAttribute
    public void setEind (String eind) {
        this.eind = eind;
    }

    @DynamoDBAttribute
    public String getTeruggebrachtOp () {
        return teruggebrachtOp;
    }

    @DynamoDBAttribute
    public void setTeruggebrachtOp (String teruggebrachtOp) {
        this.teruggebrachtOp = teruggebrachtOp;
    }

    @DynamoDBAttribute
    public String getOpmerking () {
        return opmerking;
    }

    @DynamoDBAttribute
    public void setOpmerking (String opmerking) {
        this.opmerking = opmerking;
    }

    @Override
    public String toString () {
        return "Uitlening{" +
                "id='" + id + '\'' +
                ", organisatie=" + organisatie +
                ", magazijn=" + magazijn +
                ", start=" + start +
                ", eind=" + eind +
                ", teruggebrachtOp=" + teruggebrachtOp +
                ", opmerking='" + opmerking + '\'' +
                '}';
    }
}
