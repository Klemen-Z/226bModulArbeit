package com.main.ptsd;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
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
    @SuppressWarnings("unchecked")
    public void dataStore(Date d, Integer wave, String fileName) throws IOException {
        FileWriter file = new FileWriter(fileName);
        JSONObject json = new JSONObject();
        if (checkFileContentExists(fileName)){
            json.put("date", "" + d + "");
            json.put("wave", wave);
            try{
                file.append(", ").append(json.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            json.put("date", "" + d + "");
            json.put("wave", wave);
            try {
                file.write(json.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean checkFileContentExists(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        return br.readLine() != null;
    }

    @Test
    public void jsonFileTest() throws IOException {
        dataStore(new Date(), 5, "Highscore.json");
    }
}
