package com.brigido.senior.rest;

import com.brigido.senior.dto.VoteValidationDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class CpfRest {

    @Autowired
    private Gson gson;

    private final String URL = "https://user-info.herokuapp.com/users/%s";

    public boolean isCpfWithPermissionToVote(String cpf) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(URL.formatted(cpf));
            String responseBody = httpClient.execute(httpGet, response -> EntityUtils.toString(response.getEntity()));

            VoteValidationDTO voteValidationDTO = gson.fromJson(responseBody, VoteValidationDTO.class);
            return voteValidationDTO.isAbleToVote();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
