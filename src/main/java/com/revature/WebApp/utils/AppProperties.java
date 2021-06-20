package com.revature.WebApp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class populates settings structures and builds data source for spring boot application.
 * If local /resources/application.properties file is located, settings are loaded from there.
 * Otherwise settings are loaded from environmental variables.
 */
@Component
public class AppProperties {
    private String appPropsFilePath;
    private Properties appProperties;
    private boolean appPropertiesFileExists;
    private static AppProperties appPropsRef;

    //data source
    private DataSource dataSource;


    //Server Configs
    private String serverPort;
    private String h2consoleEnabled;

    //API Configs
    private String rapidAPIKey;
    private String omdbAPIKey;
    private String imdbAPIKey;


    //settings map
    private Map<String, Object> settings;

    @Autowired
    public AppProperties() {
        settings = new HashMap<>();
        appPropsFilePath = "src/main/resources/application.properties";
        File checkFile = new File(appPropsFilePath);
        if (checkFile.exists()) {
            appPropertiesFileExists = true;
            try (FileReader propFile = new FileReader(appPropsFilePath)) {
                appProperties = new Properties();
                appProperties.load(propFile);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: Change this later
            }
        }

        //sSet up datasource
        dataSource = buildDataSource(
                getProperty("spring.datasource.url"),
                getProperty("spring.datasource.username"),
                getProperty("spring.datasource.password"),
                getProperty("spring.datasource.driver")
        );

        //set up server
        settings.put("server.port", getProperty("server.port"));
        settings.put("spring.h2.console.enabled", getProperty("spring.h2.console.enabled"));

        //set up external APIs
        settings.put("rapidapi.key", getProperty("rapidapi.key"));
        settings.put("omdbapi.key", getProperty("omdbapi.key"));
        settings.put("imdbapi.key", getProperty("imdbapi.key"));
    }

    public static AppProperties getAppProperties() {
        if(appPropsRef == null) {
            appPropsRef = new AppProperties();
        }
        return appPropsRef;
    }


    private DataSource buildDataSource(String dataSourceURL, String dataSourceUsername, String dataSourcePassword, String dataSourceDriver) {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(dataSourceURL);
        dataSourceBuilder.username(dataSourceUsername);
        dataSourceBuilder.password(dataSourcePassword);
        dataSourceBuilder.driverClassName(dataSourceDriver);
        return dataSourceBuilder.build();
    }

    private String getProperty(String propName) {
        if(appPropertiesFileExists) {
            return appProperties.getProperty(propName);
        }
        else {
            return System.getenv(getEnvRepresentation(propName));
        }
    }

    private String getEnvRepresentation(String s) {
        return s.replace(".", "_").toUpperCase();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getSetting(String s) {
        return (String)settings.get(s);
    }

    public Map<String, Object> getSettings() {
        return settings;
    }

}
