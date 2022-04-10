package com.main.ptsd;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Date;
import java.util.Random;

public class TestMain {

    //Test for Scoreboard with JSON implementation
    @Test
    public void scoreboardTest(){
        Scoreboard bob = new Scoreboard();
        bob.getList();
        System.out.println("Highest: " + bob.getHighscore());
    }

    //Test for a JSON Methods I need to test whenever
    @Test
    public void RandomJSONMethodTesting(){
        try {
            DataDealer dealer = new DataDealer("Highscore.json");
            for (Long i: dealer.getValues().values()) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Test for datastore method
    @Test
    public void DataStoringTest() throws IOException, InterruptedException {
        DataDealer dealer = new DataDealer("Highscore.json");
        dealer.dataStore(new Date(), new Random().nextInt(4000));
        Thread.sleep(1250);
        dealer.dataStore(new Date(), new Random().nextInt(4000));
    }
}
