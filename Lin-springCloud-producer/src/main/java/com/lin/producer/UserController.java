package com.lin.producer;

import com.lin.dto.ServiceResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @RequestMapping(value = "find")
    public ServiceResponse findUser(){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setResult("hello");
        return serviceResponse;
    }
}
