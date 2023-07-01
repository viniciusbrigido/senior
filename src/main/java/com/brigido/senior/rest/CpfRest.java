package com.brigido.senior.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class CpfRest {

    @Autowired
    private Gson gson;

    private static final String URL = "https://user-info.herokuapp.com/users/%s";

    public static boolean isCpfWithPermissionToVote(String cpf) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(URL.formatted(cpf));
            String responseBody = httpClient.execute(httpGet, response -> EntityUtils.toString(response.getEntity()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
