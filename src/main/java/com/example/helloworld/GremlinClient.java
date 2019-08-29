package com.example.helloworld;

import com.gremlin.ApplicationCoordinates;
import com.gremlin.GremlinCoordinatesProvider;
import com.gremlin.GremlinService;
import com.gremlin.GremlinServiceFactory;
import com.gremlin.http.client.GremlinApacheHttpRequestInterceptor;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GremlinClient {

    public void run() throws IOException {

        final GremlinCoordinatesProvider coordinatesProvider = new GremlinCoordinatesProvider() {
            @Override
            public ApplicationCoordinates initializeApplicationCoordinates() {
                return new ApplicationCoordinates.Builder()
                        .withType("HelloWorldRestful")
                        .withField("name", "eric h")
                        .build();
            }
        };

        final GremlinServiceFactory gremlinServiceFactory = new GremlinServiceFactory(coordinatesProvider);
        final GremlinService gremlinService = gremlinServiceFactory.getGremlinService();

        final HttpRequestInterceptor gremlinHttpInterceptor =
                new GremlinApacheHttpRequestInterceptor(gremlinService, "sample");

        final HttpClient outgoingHttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig
                        .custom()
                        .setConnectTimeout(500)
                        .setSocketTimeout(1000)
                        .build()
                )
                .addInterceptorLast(gremlinHttpInterceptor)
                .build();

        HttpGet getRequest = new HttpGet("http://localhost:8080/hello");
        HttpResponse response = outgoingHttpClient.execute(getRequest);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
    }
}
