package com.example.customertopic;


import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.*;

@Controller
@RequestMapping("Custumer")
public class Custumer  {




    @RequestMapping("receive")
    @JmsListener(destination = "topicName_test",containerFactory = "custumerizedContainerFactory")
    public void receiveMessage() {


    }
}


