package com.example.customertopic;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.checkerframework.checker.units.UnitsTools.cd;

@Configuration
public class TestConfig {






   // @Bean
//    Cdplayer cdplayer(Disc disc){
//        Cdplayer cdplayer=new Cdplayer();
//        cdplayer.setDisc(disc);
//
//        return  cdplayer;
//    }


//@authweied
        @Bean
    Cdplayer cdplayer( ){

        return new  Cdplayer();
    }

//        @Bean
//    Cdplayer cdplayer(Disc disc){
//
//        return new  Cdplayer(disc);
//    }




    @Bean(name = "d")
    Disc disc(){

        return  new Disc();
    }



}



