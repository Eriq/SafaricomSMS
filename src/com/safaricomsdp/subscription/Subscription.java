package com.safaricomsdp.subscription;

import com.safaricomsdp.SafaricomSDP;
import com.safaricomsdp.Service;
import com.safaricomsdp.jsonbuilders.Datum;
import com.safaricomsdp.jsonbuilders.RequestParam;
import com.safaricomsdp.jsonbuilders.RequestPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.safaricomsdp.SafaricomSDP.SERVICE_ACTIVATE;
import static com.safaricomsdp.Service.sendRequest;

public class Subscription {

    public static void main(String[] args) throws IOException {

        /* Initialize SDK with the app credentials */
        Service.getToken(SafaricomSDP.userName,SafaricomSDP.password);
        System.out.println("Token: " + Service.token);

        /* Set Request Parameters */
        Datum OfferCode = new Datum("OfferCode", SafaricomSDP.OfferCode);
        Datum CpId = new Datum("CpId", SafaricomSDP.CpId);
        Datum Msisdn = new Datum("Msisdn", "254702080051");

        /* Initialize SMS Requset Packet */
        List<Datum> params = new ArrayList();
        params.add(OfferCode); params.add(Msisdn); params.add(CpId);
        RequestParam param = new RequestParam(params);

        /* Execute Operation */
        /* Activate */
        RequestPacket data = new RequestPacket("ACTIVATE", param);
        System.out.println(sendRequest(data, SERVICE_ACTIVATE));

        /* Deactivate */
        //RequestPacket data = new RequestPacket("DEACTIVATE", param);
        //System.out.println(sendRequest(data, SERVICE_DEACTIVATE));



    }

}
