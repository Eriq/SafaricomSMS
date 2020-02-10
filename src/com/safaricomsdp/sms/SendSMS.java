package com.safaricomsdp.sms;

import com.safaricomsdp.SafaricomSDP;
import com.safaricomsdp.Service;
import com.safaricomsdp.jsonbuilders.Datum;
import com.safaricomsdp.jsonbuilders.RequestParam;
import com.safaricomsdp.jsonbuilders.RequestPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.safaricomsdp.SafaricomSDP.SERVICE_SMS;
import static com.safaricomsdp.Service.sendRequest;

public class SendSMS {

    public static void main(String[] args) throws IOException {

        /* Initialize SDK with the app credentials */
        Service.getToken(SafaricomSDP.userName,SafaricomSDP.password);
        System.out.println("Token: " + Service.token);

        /* Set Request Parameters */
        Datum LinkId = new Datum("LinkId",SafaricomSDP.LinkId);
        Datum OfferCode = new Datum("OfferCode", SafaricomSDP.OfferCode);
        Datum CpId = new Datum("CpId", SafaricomSDP.CpId);

        /* Set Message Recipient */
        Datum Msisdn = new Datum("Msisdn", "254702080051");

        /* Set Message Content */
        Datum Content = new Datum("Content", "Hello World");


        /* Initialize SMS Requset Packet */
        List<Datum> params = new ArrayList();
        params.add(LinkId); params.add(Msisdn); params.add(Content); params.add(OfferCode); params.add(CpId);
        RequestParam param = new RequestParam(params);
        RequestPacket data = new RequestPacket("SendSMS",param);

        /* Send SMS */
        System.out.println(sendRequest(data,SERVICE_SMS));

    }
}