package com.safaricomsdp;

public final class SafaricomSDP {

    private static final String BASE_URL = "https://dtsvc.safaricom.com:8480"; //Url for Testbed Environment

    public static final String SERVICE_LOGIN = BASE_URL + "/api/auth/login";
    public static final String SERVICE_REFRESH = BASE_URL + "/api/auth/RefreshToken";
    public static final String SERVICE_SMS = BASE_URL + "/api/public/SDP/sendSMSRequest";
    public static final String SERVICE_BULK = BASE_URL + "/api/public/CMS/bulksms";
    public static final String SERVICE_ACTIVATE = BASE_URL + "/api/public/SDP/activate";
    public static final String SERVICE_DEACTIVATE = BASE_URL + "/api/public/SDP/deactivate";
    public static final String SERVICE_CPNOTIFICATION = BASE_URL + "/api/public/SDP/CPNotification";

    public static final String userName = "Etiqet_apiuser"; //Username allocated by the SDP to the partner after successful registration.
    public static final String password = "Admin@123"; //Password allocated by the SDP to the partner after successful registration.

    public static final String LinkId = "00010310189519161781865526";    //This ID is generated when a user requests for a service in SDP.
    public static final String OfferCode = "1003"; //This is allocated by partner or Safaricom on successful service creation from SDP.
    public static final String CpId = "10"; //Id allocated to partner after successful registration.
    public static final String oa = "SFGHKG"; //Originating address assigned to partner after successful registration.

}
