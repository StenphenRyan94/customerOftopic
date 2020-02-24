package com.example.customertopic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class test {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
     String beanNames[]=   applicationContext.getBeanNamesForType(Disc.class);
        System.out.println(Arrays.deepToString(beanNames));
        Cdplayer cdPlayer = applicationContext.getBean(Cdplayer.class);
        cdPlayer.cdplay();

    }
}
