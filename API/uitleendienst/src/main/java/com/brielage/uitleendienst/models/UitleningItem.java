package com.brielage.uitleendienst.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Date;

@DynamoDBTable (tableName = "uitleningItem")
public class UitleningItem {
    private String          id;
    private Uitlening       uitlening;
    private UitleenbaarItem item;
    private int             aantal;
    private Date            teruggebrachtOp;
    private int             aantalTeruggebracht;

    public UitleningItem () {}

    public UitleningItem (
            Uitlening uitlening,
            UitleenbaarItem item,
            int aantal,
            Date teruggebrachtOp,
            int aantalTeruggebracht) {
        this.uitlening           = uitlening;
        this.item                = item;
        this.aantal              = aantal;
        this.teruggebrachtOp     = teruggebrachtOp;
        this.aantalTeruggebracht = aantalTeruggebracht;
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
    public Uitlening getUitlening () {
        return uitlening;
    }

    @DynamoDBAttribute
    public void setUitlening (Uitlening uitlening) {
        this.uitlening = uitlening;
    }

    @DynamoDBAttribute
    public UitleenbaarItem getItem () {
        return item;
    }

    @DynamoDBAttribute
    public void setItem (UitleenbaarItem item) {
        this.item = item;
    }

    @DynamoDBAttribute
    public int getAantal () {
        return aantal;
    }

    @DynamoDBAttribute
    public void setAantal (int aantal) {
        this.aantal = aantal;
    }

    @DynamoDBAttribute
    public Date getTeruggebrachtOp () {
        return teruggebrachtOp;
    }

    @DynamoDBAttribute
    public void setTeruggebrachtOp (Date teruggebrachtOp) {
        this.teruggebrachtOp = teruggebrachtOp;
    }

    @DynamoDBAttribute
    public int getAantalTeruggebracht () {
        return aantalTeruggebracht;
    }

    @DynamoDBAttribute
    public void setAantalTeruggebracht (int aantalTeruggebracht) {
        this.aantalTeruggebracht = aantalTeruggebracht;
    }

    @Override
    public String toString () {
        return "UitleningItem{" +
                "id='" + id + '\'' +
                ", uitlening=" + uitlening +
                ", item=" + item +
                ", aantal=" + aantal +
                ", teruggebrachtOp=" + teruggebrachtOp +
                ", aantalTeruggebracht=" + aantalTeruggebracht +
                '}';
    }
}
