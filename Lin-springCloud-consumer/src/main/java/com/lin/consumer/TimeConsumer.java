package com.lin.consumer;

import com.lin.consumer.Hystrix.ConsumerHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="springCloud-product",fallback = ConsumerHystrix.class)
public interface TimeConsumer {
    @RequestMapping(name = "/time/fetch")
    public Object fetchTime();
}
