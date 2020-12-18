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

package org.oisp.apiclients.alerts;

import org.oisp.apiclients.ApiFatalException;
import org.oisp.apiclients.ApiNotAuthorizedException;
import org.oisp.apiclients.ApiNotFatalException;
import org.oisp.collection.RulesWithObservation;

import java.util.List;


public interface AlertsApi {

    void pushAlert(List<RulesWithObservation> rulesWithObservation) throws ApiFatalException, ApiNotAuthorizedException, ApiNotFatalException;
    String refreshToken() throws ApiFatalException, ApiNotAuthorizedException;
}
