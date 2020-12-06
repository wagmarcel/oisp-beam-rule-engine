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

package org.oisp.conf;


import com.google.gson.Gson;

@SuppressWarnings({"checkstyle:methodname", "checkstyle:parametername", "checkstyle:membername", "PMD.TooManyFields" })
public class ExternalConfig {
    private String dashboard_user;
    private String dashboard_password;
    private String dashboard_url;
    private String kafka_servers;
    private String kafka_zookeeper_quorum;
    private String kafka_observations_topic;
    private String kafka_rule_engine_topic;
    private String kafka_heartbeat_topic;
    private int kafka_heartbeat_interval;
    private String application_name;


    public String getDashboard_user() {
        return dashboard_user;
    }

    public void setDashboard_user(String dashboard_user) {
        this.dashboard_user = dashboard_user;
    }

    public String getDashboard_password() {
        return dashboard_password;
    }

    public void setDashboard_password(String dashboard_password) {
        this.dashboard_password = dashboard_password;
    }

    public String getKafka_observations_topic() {
        return kafka_observations_topic;
    }

    public void setKafka_observations_topic(String kafka_topic) {
        this.kafka_observations_topic = kafka_topic;
    }

    public String getKafka_rule_engine_topic() {
        return kafka_rule_engine_topic;
    }

    public void setKafka_rule_engine_topic(String kafka_topic) {
        this.kafka_rule_engine_topic = kafka_topic;
    }

    public String getKafka_heartbeat_topic() {
        return kafka_heartbeat_topic;
    }

    public void setKafka_heartbeat_topic(String kafka_topic) {
        this.kafka_heartbeat_topic = kafka_topic;
    }

    public int getKafka_heartbeat_interval() {
        return kafka_heartbeat_interval;
    }

    public void setKafka_heartbeat_interval(int interval) {
        this.kafka_heartbeat_interval = interval;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getDashboard_url() {
        return dashboard_url;
    }

    public void setDashboard_url(String dashboard_url) {
        this.dashboard_url = dashboard_url;
    }

    public String getKafka_servers() {
        return kafka_servers;
    }

    public void setKafka_servers(String kafka_servers) {
        this.kafka_servers = kafka_servers;
    }

    public String getKafka_zookeeper_quorum() {
        return kafka_zookeeper_quorum;
    }

    public void setKafka_zookeeper_quorum(String kafka_zookeeper_quorum) {
        this.kafka_zookeeper_quorum = kafka_zookeeper_quorum;
    }



    @Override
    public String toString() {
        String sep = ", ";
        StringBuilder builder = new StringBuilder()
                .append("kafka_observations_topic: ").append(getKafka_observations_topic()).append(sep)
                .append("kafka_rule_engine_topic: ").append(getKafka_rule_engine_topic()).append(sep)
                .append("kafka_heartbeat_topic: ").append(getKafka_heartbeat_topic()).append(sep)
                .append("kafka_heartbeat_interval: ").append(getKafka_heartbeat_interval()).append(sep)
                .append("application_name: ").append(getApplication_name()).append(sep)
                .append("dashboard_url: ").append(getDashboard_url()).append(sep)
                .append("kafka_servers: ").append(getKafka_servers()).append(sep)
                .append("kafka_zookeeper_quorum: ").append(getKafka_zookeeper_quorum());

        return builder.toString();
    }

    public static ExternalConfig getConfigFromString(String args) {
        Gson g = new Gson();
        return g.fromJson(args, ExternalConfig.class);
    }

    public Config getConfig() {


        Config config = new Config()
                .put(Config.DASHBOARD_URL_PROPERTY, getDashboard_url())
                .put(Config.KAFKA_TOPIC_OBSERVATION_PROPERTY, getKafka_observations_topic())
                .put(Config.KAFKA_TOPIC_RULES_UPDATE_PROPERTY, getKafka_rule_engine_topic())
                .put(Config.KAFKA_TOPIC_HEARTBEAT_PROPERTY, getKafka_heartbeat_topic())
                .put(Config.KAFKA_HEARTBEAT_INTERVAL_PROPERTY, getKafka_heartbeat_interval())
                .put(Config.KAFKA_URI_PROPERTY, getKafka_servers())
                .put(Config.KAFKA_ZOOKEEPER_PROPERTY, getKafka_zookeeper_quorum());
        return config;
    }
}
