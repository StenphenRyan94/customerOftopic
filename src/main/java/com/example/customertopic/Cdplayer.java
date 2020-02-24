package com.example.customertopic;

import org.springframework.beans.factory.annotation.Autowired;

public class Cdplayer {



    Disc disc;

//
//    Cdplayer(Disc disc){
//        this.disc=disc;
//    }

//    public void setDisc(Disc disc) {
//        this.disc = disc;
//    }






    public void cdplay(){
        disc.disc();
        System.out.println("cdplayer");

    }


    @Autowired
    public void setDisc(Disc disc) {
        this.disc=disc;
    }
}
