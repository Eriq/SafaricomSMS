package com.safaricomsdp;

import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.Response;
import okhttp3.RequestBody;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;

public class Service {

    private final String host;
    private final OkHttpClient client;

    public Service(String host, OkHttpClient client) {
        super();
        this.host = host;
        this.client = client;
    }


    public String getToken(Credentials credentials) throws IOException {
        HttpUrl route = HttpUrl.parse(host + "/api/auth/login");
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
            return response.body().string();
        }
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
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

    public static void main(String[] args) throws IOException {
        String host = "https://dtsvc.safaricom.com:8480";
        String username = "Etiqet_apiuser";
        String password = "Admin@123";

        OkHttpClient client = getUnsafeOkHttpClient();

        Service test = new Service(host,client);
        Credentials user = new Credentials();
        user.setUsername(username);
        user.setPassword(password);

        System.out.println(test.getToken(user));
    }
}
