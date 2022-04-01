package com.main.ptsd;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestMain {

    @Test
    public void Test() throws InterruptedException {
        Scoreboard bob = new Scoreboard();
        Date date = new Date();
        bob.addEntry(10, date);
        Thread.sleep(1500);
        date = new Date();
        bob.addEntry(20, date);
        Thread.sleep(1500);
        date = new Date();
        bob.addEntry(100, date);
        Thread.sleep(1500);
        date = new Date();
        bob.addEntry(50, date);

        System.out.println(bob.getHighscore());
    }
}
