package com.lin.dto;

import java.io.Serializable;

public class ServiceResponseCode implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer SUCCESS = Integer.valueOf(200);

    public static final Integer ERROR = Integer.valueOf(400);

    public static final Integer SERVICE_ERROR = Integer.valueOf(500);

    public static final Integer UNAUTHORIZED = Integer.valueOf(401);
}
