package com.safaricomsdp.jsonbuilders;

import com.safaricomsdp.SafaricomSDP;

public class DataSet {

    private String userName;
    private String channel;
    private String packageId;
    private String oa;
    private String[] msisdn;
    private String message;
    private String uniqueId;
    private String actionResponseURL;

    public DataSet(String[] msisdn, String message) {
        super();
        this.userName = SafaricomSDP.userName;
        this.channel = setChannel("SMS");
        this.packageId = setPackageId("4391");
        this.oa = SafaricomSDP.oa;
        this.msisdn = msisdn;
        this.message = message;
        this.uniqueId = setUniqueId();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChannel() {
        return channel;
    }

    public String setChannel(String sms) {
        return sms;
    }

    public String getPackageId() {
        return packageId;
    }

    public String setPackageId(String packageId) {
        return packageId;
    }

    public String getOa() {
        return oa;
    }

    public void setOa(String oa) {
        this.oa = oa;
    }

    public String[] getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String[] msisdn) {
        this.msisdn = msisdn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String setUniqueId() {
        Double rand = Math.random() * 1000000000;
        return rand.toString();
    }

    public String getActionResponseURL() {
        return actionResponseURL;
    }

    public void setActionResponseURL(String actionResponseURL) {
        this.actionResponseURL = actionResponseURL;
    }

}