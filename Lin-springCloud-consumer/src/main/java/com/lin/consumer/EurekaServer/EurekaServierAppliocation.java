package com.lin.consumer.EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServierAppliocation {
    /**
     * 服务注册中心
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(EurekaServierAppliocation.class,args);
    }
}
