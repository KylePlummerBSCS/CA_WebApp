package com.revature.WebApp.AllSamples.entities;

import javax.persistence.*;

/**
 * This entity can be used as a template for others. Currently ID is an auto generating int sequence.
 * Simply label a class @Entity, and give it an @Table name, make sure to include an Integer @Id with @GeneratedValue
 * strategy = GenerationType.IDENTITY. Then add any number of private fields for table columns and auto-generate some
 * getters and setters. Override toString for convenience, but it's unnecessary when jackson can easily generate json.
 */
@Entity
@Table(name="test_entity")
public class PrototypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String string;
    private Double dbl;
    private Boolean bool;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Double getDbl() {
        return dbl;
    }

    public void setDbl(Double dbl) {
        this.dbl = dbl;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n" +
                "string = " + string + "\n" +
                "dbl = " + dbl + "\n"+
                "bool = " + bool + "\n";
    }

}
