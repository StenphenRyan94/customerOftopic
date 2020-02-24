package com.example.customertopic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomertopicApplicationTests {

    @Autowired
    Cdplayer cdplayer;

    @Test
    void contextLoads() {
        cdplayer.cdplay();


    }

}
