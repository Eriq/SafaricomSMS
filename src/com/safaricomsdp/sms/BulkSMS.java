package com.safaricomsdp.sms;

import com.safaricomsdp.SafaricomSDP;
import com.safaricomsdp.Service;
import com.safaricomsdp.jsonbuilders.DataSet;
import com.safaricomsdp.jsonbuilders.Bulk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.safaricomsdp.SafaricomSDP.SERVICE_BULK;
import static com.safaricomsdp.Service.sendBulkSMS;

public class BulkSMS {

    public static void main(String[] args) throws IOException {

        /* Initialize SDK with the app credentials */
        Service.getToken(SafaricomSDP.userName,SafaricomSDP.password);
        System.out.println("Token: " + Service.token);

        /* Set Request Packet Parameters */
        /* Set Recipients */
        String[] msisdn = {"254702080051","254702080051"};

        /* Set message */
        String message = "Hello World";

        /* Initialize Request Packet*/
        DataSet data = new DataSet(msisdn,message);
        List<DataSet> dataset = new ArrayList<>();
        Bulk sms = new Bulk(dataset);

        /* Send Bulk SMS */
        System.out.println(sendBulkSMS(sms, SERVICE_BULK));

    }
}
