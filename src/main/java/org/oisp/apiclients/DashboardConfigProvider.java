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

package org.oisp.apiclients;

import org.oisp.apiclients.auth.DashboardAuthApi;
import org.oisp.conf.Config;
import java.io.Serializable;
import java.security.InvalidParameterException;

public class DashboardConfigProvider implements DashboardConfig, Serializable {

    private String token;
    private final String url;

    public DashboardConfigProvider(Config userConfig) throws InvalidParameterException, InvalidDashboardResponseException {
        Object username = userConfig.get(Config.DASHBOARD_USER_PROPERTY);
        Object password = userConfig.get(Config.DASHBOARD_PASSWORD_PROPERTY);
        Object url = userConfig.get(Config.DASHBOARD_URL_PROPERTY);
        Object token = userConfig.get(Config.DASHBOARD_TOKEN_PROPERTY);


        if (token != null) {
            this.token = token.toString();
        } else {
            String usernameStr;
            String passwordStr;

            if (username != null) {
                usernameStr = username.toString();
            } else {
                throw new InvalidParameterException("No token and Dashboard username not found!");
            }

            if (password != null) {
                passwordStr = password.toString();
            } else {
                throw new InvalidParameterException("No token and Dashboard password not found!");
            }
            DashboardAuthApi dashboardAuthApi = new DashboardAuthApi(this);
            token = dashboardAuthApi.getToken(usernameStr, passwordStr);
        }

        if (url != null) {
            this.url = url.toString();
        } else {
            throw new InvalidParameterException("Dashboard URL not found!");
        }
    }

    /*private Boolean parseStrictSSLOption(Config userConfig) {
        String strictSSL = userConfig.get(Config.DASHBOARD_STRICT_SSL_VERIFICATION).toString();
        //By default strictSSL option should be enabled
        if (strictSSL == null) {
            return true;
        }
        return Boolean.valueOf(strictSSL);
    }*/

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getToken() {
        return token;
    }
}
