package com.main.ptsd;

import org.junit.jupiter.api.Test;
import java.io.*;

public class TestMain {

    @Test
    public void scoreboardTest(){
        Scoreboard bob = new Scoreboard();
        bob.getList();
        System.out.println("Highest: " + bob.getHighscore());
    }

    @Test void RandomJSONMethods(){
        try {
            DataDealer dealer = new DataDealer("Highscore.json");
            for (Long i: dealer.getValues().values()) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
