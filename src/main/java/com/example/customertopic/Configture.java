package com.example.customertopic;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.AbstractJmsListenerContainerFactory;
import org.springframework.jms.config.DefaultJcaListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Topic;



@Configuration
public class Configture {


    @Value("${spring.activemq.broker-url}")
    String ActiveMQ_URL;

    @Value("${spring.activemq.user}")
    String ActiveMQ_USER;

    @Value("${spring.activemq.password}")
    String ActiveMQ_PASSWORD;



    /*
    <beanid="XX" class="ActiveMQConnectionFactory">
    <property>brokenUrl value=''</pro..>

     */




    //自定义适配器处理类
    @Bean
    Adapter adapter(){

        return new Adapter();
    }


    @Bean
    Topic topic(){
        return  new ActiveMQTopic("topicName_test");
    }


    @Bean
    public ConnectionFactory connectionFactory(){

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(ActiveMQ_URL);
        System.out.println("ActiveMQ_URL@"+ActiveMQ_URL);
        connectionFactory.setUserName(ActiveMQ_USER);
        System.out.println("ActiveMQ_USER@"+ActiveMQ_USER);
        connectionFactory.setPassword(ActiveMQ_PASSWORD);
        System.out.println("ActiveMQ_PASSWORD@"+ActiveMQ_PASSWORD);


        return connectionFactory;

    }

    //接受消息转换器
    @Bean
    MessageConverter messageConverter(){
        MessageConverter messageConverter=new MyMessageConverter();
        return  messageConverter;
    }



    @Bean
    MessageListenerAdapter messageListenerAdapter(Adapter adapter,MessageConverter messageConverter){//监听器
        System.out.println("我是监听器"+"messageListenerAdapter");

        MessageListenerAdapter messageListenerAdapter=new MessageListenerAdapter();
        messageListenerAdapter.setDelegate(adapter);//自定义的适配器
        messageListenerAdapter.setMessageConverter(messageConverter);
//        messageListenerAdapter.setre

        //messageListenerAdapter.setDefaultListenerMethod("receiveMessage");//默认处理方法
          return  messageListenerAdapter;

    }



    @Bean
    DefaultMessageListenerContainer defaultMessageListenerContainer( ConnectionFactory connectionFactory,Topic topic,MessageListenerAdapter messageListenerAdapter ) throws JMSException {
        System.out.println("我是监听容器,"+"DefaultMessageListenerContainer");

        DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(topic);
        System.out.println("当前监听topic地址："+topic.getTopicName());
        defaultMessageListenerContainer.setMessageListener(messageListenerAdapter);//注册监听器
        defaultMessageListenerContainer.setPubSubDomain(true);
        return  defaultMessageListenerContainer;


    }



    @Bean
    DefaultJmsListenerContainerFactory custumerizedContainerFactory(){
        System.out.println("我是监听工厂");

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        //设置连接数
        factory.setConcurrency("3-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        return factory;

    }
}
