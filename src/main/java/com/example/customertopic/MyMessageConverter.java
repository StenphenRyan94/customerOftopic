package com.example.customertopic;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.*;

public class MyMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {//java-->jms对象
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {//fromMessage是用来把一个JMS Message转换成对应的Java对象
        System.out.println("--->接受消息转换start<------");
        Object object=null;
        if (message instanceof TextMessage){
            System.out.println("接受消息转换 TextMessage@");
            object= ((TextMessage) message).getText();
        }
       else if(message instanceof MapMessage){
            System.out.println("接受消息转换 MapMessage@");
            //转换成map

//            message.getObjectProperty();
            Map<Object,Object> map=new HashMap<>();
            map.put("testKey","testMap");
            object= map ;
        }else{
           //pojo转json

    object=message;
        }


        return object;
    }
}
