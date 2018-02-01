package com.lin.consumer.Hystrix;

import feign.Request;
import feign.Retryer;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 此类是feign的配置类，主要是feignRetryer，作用是重试
 */
@Component
public class FeignConfig extends FeignClientsConfiguration{
    @Bean
    public Request.Options feignOptions(){
        return new Request.Options(/** connectTimeoutMillis **/
                1 * 1000, /** readTimeoutMillis **/
                1 * 1000);
    }

    @Bean
    public Retryer feignRetryer(){
        return Retryer.Default.NEVER_RETRY;
    }
}
