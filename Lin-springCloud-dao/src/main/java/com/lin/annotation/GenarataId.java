package com.lin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//注解作用目标
@Retention(RetentionPolicy.RUNTIME)//注解保留位置
public @interface GenarataId {

}
