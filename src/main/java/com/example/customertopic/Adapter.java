package com.example.customertopic;


import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import javax.jms.*;
import java.util.Arrays;
import java.util.Map;

//自定义处理器
public class Adapter  {

    @Autowired
    JmsTemplate jmsTemplate;



    public void handleMessage(Object message)  throws JMSException {
        System.out.println("handleMessage--->start--->");


        if (message instanceof String) {

            System.out.println("ConsumerListener通过handleMessage接收到一个纯文本消息，消息内容是：" + message);

        }else if (message instanceof Map){
            System.out.println("ConsumerListener通过handleMessage接收到一个map消息，消息内容是：" + message);


        }


    }




    public void receiveMessage(Message message) {
        System.out.println("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message);
    }

}
