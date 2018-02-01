package com.lin.consumer.Hystrix;

import com.lin.consumer.TimeConsumer;
import org.springframework.stereotype.Component;

@Component
public class ConsumerHystrix implements TimeConsumer{
    @Override
    public Object fetchTime(){
        System.out.println("in hystrix");
        return  null;
    }
}
