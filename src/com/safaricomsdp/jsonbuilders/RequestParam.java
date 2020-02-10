package com.safaricomsdp.jsonbuilders;

import java.util.List;

public class RequestParam {

    private List<Datum> data = null;

    public RequestParam(List<Datum> data) {
        super();
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public RequestParam withData(List<Datum> data) {
        this.data = data;
        return this;
    }

}