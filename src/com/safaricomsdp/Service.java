package com.safaricomsdp;

import com.google.gson.Gson;
import com.safaricomsdp.jsonbuilders.Bulk;
import com.safaricomsdp.jsonbuilders.Credentials;
import com.safaricomsdp.jsonbuilders.RequestPacket;
import com.safaricomsdp.jsonbuilders.Token;
import okhttp3.*;
import okhttp3.Response;
import okhttp3.RequestBody;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;

public final class Service {

    public static String token = null;
    public static String refreshToken = null;
    public static String msg = null;

    public static void getToken(String userName, String password) throws IOException {
        Credentials credentials = new Credentials(userName,password);
        OkHttpClient client = Service.getUnsafeOkHttpClient();

        HttpUrl route = HttpUrl.parse(SafaricomSDP.SERVICE_LOGIN);
        Gson gson = new Gson();
        String jsonString = gson.toJson(credentials);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonString);

        Request request = new Request.Builder()
                .header("X-Requested-With", "XMLHttpRequest")
                .url(route)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Token t  = gson.fromJson(response.body().string(), Token.class);
            Service.token  = t.getToken();
            Service.refreshToken = t.getRefreshToken();
            Service.msg = t.getMsg();
        }
    }

    public static String sendBulkSMS(Bulk sms, String host) throws IOException {
        OkHttpClient client = Service.getUnsafeOkHttpClient();

        HttpUrl route = HttpUrl.parse(host);
        Gson gson = new Gson();

        String jsonString = gson.toJson(sms);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonString);

        Request request = new Request.Builder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("X-Authorization", "Bearer " + token)
                .url(route)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static String sendRequest(RequestPacket requestPacket, String host) throws IOException {
        OkHttpClient client = Service.getUnsafeOkHttpClient();

        HttpUrl route = HttpUrl.parse(host);
        Gson gson = new Gson();

        String jsonString = gson.toJson(requestPacket);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonString);

        Request request = new Request.Builder()
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("X-Authorization", "Bearer " + token)
                .url(route)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
