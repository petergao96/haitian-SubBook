

package com.example.peter.subbook;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by peter on 2018-02-02.
 */

public class Subscription implements Serializable {
    private String name;
    private Date date;
    private String charge;
    private String comment;

    Subscription(String name, String charge, String comment){
        this.name = name;
        this.charge = charge;
        this.comment = comment;
        date = new Date();
    }

    Subscription(String name, Date date, String charge, String comment){
        this.name = name;
        this.charge = charge;
        this.comment = comment;
        this.date = date;
    }

    public String getName(){ return name;}
    public Date getDate(){
        return date;
    }
    public String getCharge(){return charge;}
    public String getComment(){return comment;}






}
