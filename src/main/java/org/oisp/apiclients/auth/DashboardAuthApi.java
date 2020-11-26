/*
 * Copyright (c) 2016 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.oisp.apiclients.auth;

import org.oisp.apiclients.ApiClientHelper;
import org.oisp.apiclients.CustomRestTemplate;
import org.oisp.apiclients.DashboardConfigProvider;
import org.oisp.apiclients.InvalidDashboardResponseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class DashboardAuthApi implements AuthApi, Serializable {

    private final String url;
    private final String refreshUrl;
    private final String token;
    private DashboardConfigProvider config;
    private static final String PATH = "/v1/api/auth";
    private transient RestTemplate template;

    public DashboardAuthApi(DashboardConfigProvider dashboardConfig) {
        this(dashboardConfig, CustomRestTemplate.build(dashboardConfig).getRestTemplate());
    }

    public DashboardAuthApi(DashboardConfigProvider dashboardConfig, RestTemplate restTemplate) {
        token = dashboardConfig.getToken();
        url = dashboardConfig.getUrl() + PATH;
        refreshUrl = url + "/refresh";
        template = restTemplate;
        config = dashboardConfig;
    }

    private String getToken() {
        return token;
    }

    @Override
    public RefreshToken getRefreshToken() throws InvalidDashboardResponseException {

        HttpHeaders headers = ApiClientHelper.getHttpHeaders(getToken());
        HttpEntity req = new HttpEntity<>(null, headers);
        RefreshToken refreshToken;

        try {
            ResponseEntity<RefreshToken> resp = template.exchange(refreshUrl, HttpMethod.POST, req, RefreshToken.class);
            if (resp.getStatusCode() != HttpStatus.OK) {
                throw new InvalidDashboardResponseException("Invalid response - " + resp.getStatusCode());
            }
            refreshToken = resp.getBody();
        } catch (RestClientException e) {
            throw new InvalidDashboardResponseException("Unknown dashboard response error.", e);
        }
        return refreshToken;
    }

    @Override
    public AccessToken updateAccessToken() throws InvalidDashboardResponseException {

        HttpHeaders headers = ApiClientHelper.getHttpHeaders(getToken());
        HttpEntity req = new HttpEntity<>(null, headers);
        AccessToken accessToken;

        try {
            ResponseEntity<AccessToken> resp = template.exchange(refreshUrl, HttpMethod.PUT, req, AccessToken.class);
            if (resp.getStatusCode() != HttpStatus.OK) {
                throw new InvalidDashboardResponseException("Invalid response - " + resp.getStatusCode());
            }
            accessToken = resp.getBody();
        } catch (RestClientException e) {
            throw new InvalidDashboardResponseException("Unknown dashboard response error.", e);
        }
        return accessToken;
    }

    private void readObject(ObjectInputStream o)
            throws IOException, ClassNotFoundException {
        o.defaultReadObject();
        template = CustomRestTemplate.build(config).getRestTemplate();
    }
}
