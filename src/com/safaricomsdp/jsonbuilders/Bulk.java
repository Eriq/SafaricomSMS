package com.safaricomsdp.jsonbuilders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Bulk {
    public String timeStamp;
    public List<DataSet> dataSet = null;

    public Bulk(List<DataSet> dataSet) {
        super();
        this.timeStamp = setRequestTimeStamp();
        this.dataSet = dataSet;
    }

    public String setRequestTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
