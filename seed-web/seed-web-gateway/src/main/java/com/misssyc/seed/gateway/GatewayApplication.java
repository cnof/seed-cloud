package com.misssyc.seed.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 33992
 * @since 2024/2/5
 **/
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(GatewayApplication.class, args);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
