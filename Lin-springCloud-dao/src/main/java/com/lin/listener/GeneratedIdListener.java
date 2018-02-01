package com.lin.listener;

import com.lin.annotation.GenarataId;
import com.lin.util.GenerateMongoCollectionId;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import sun.reflect.Reflection;

import javax.annotation.Resource;
import java.lang.reflect.Field;

@Component
public class GeneratedIdListener extends AbstractMongoEventListener<Object>{
    @Resource
    private GenerateMongoCollectionId generateMongoCollectionId;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> source) {
        super.onBeforeConvert(source);
        Object obj = source.getSource();
        if (source != null){
            ReflectionUtils.doWithFields(source.getSource().getClass(),new ReflectionUtils.FieldCallback(){
                public void doWith(Field field) throws IllegalArgumentException,IllegalAccessException{
                    if (field.isAnnotationPresent(GenarataId.class)){
                        //设置自增ID
                        if(null==field.get(source.getSource())){
                            field.set(source.getSource(), generateMongoCollectionId.getNextId(source.getSource().getClass()));
                        }
                    }
                }
            });
        }
    }
}
