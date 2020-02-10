package com.safaricomsdp.jsonbuilders;

public class Datum {

    private String name;
    private String value;

    public Datum() {
    }

    public Datum(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Datum withName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Datum withValue(String value) {
        this.value = value;
        return this;
    }

}
