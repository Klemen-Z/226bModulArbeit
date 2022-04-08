package com.main.ptsd;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Date;

public class TestMain {

    @Test
    public void scoreboardTest() throws InterruptedException {
        Scoreboard bob = new Scoreboard();
        Date date = new Date();
        bob.addEntry(10, date);
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(20, date);
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(100, date);
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(50, date);

        bob.getList();
        System.out.println("Highest: " + bob.getHighscore());
    }
    @Test
    public void jsonFileTest() throws IOException, InterruptedException {
        DataDealer dealer = new DataDealer("Highscore.json");
        dealer.dataStore(new Date(), 5);
        Thread.sleep(1000);
        dealer.dataStore(new Date(), 10);
    }

    @Test void RandomJSONMethods(){
        try {
            DataDealer dealer = new DataDealer("Highscore.json");
            dealer.JSONFileArrayParser();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
