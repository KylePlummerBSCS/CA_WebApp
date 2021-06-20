
package com.revature.WebApp;

import com.revature.WebApp.utils.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class WebAppApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebAppApplication.class);
        app.setDefaultProperties(AppProperties.getAppProperties().getSettings());
        app.run(args);
    }
}