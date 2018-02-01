package com.lin.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.linx")
@EnableAutoConfiguration
/*@Configuration
@Controller*/
public class Application {
    /*@RequestMapping("/")
    public String hello(){
        return "hello horld~";
    }*/

    //@Override
    //public Health health() {
    //    return Health.up().withDetail("status","ok").build();
    //}

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }




}
