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
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(20, date);
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(100, date);
        Thread.sleep(1000);
        date = new Date();
        bob.addEntry(50, date);

        System.out.println(bob.getHighscore());
    }
    @SuppressWarnings("unchecked")
    public void dataStore(Date d, Integer wave, String fileName) throws IOException {
        JSONObject json = new JSONObject();
        JSONArray jsonA;
        if (checkFileContentExists(fileName)){
            System.out.println("I append");
            JSONParser jsonP = new JSONParser();
            json.put("date", "" + d + "");
            json.put("wave", wave);
            try (FileWriter file = new FileWriter(fileName); FileReader reader = new FileReader(fileName)){
                jsonA = (JSONArray) jsonP.parse(reader);
                jsonA.add(json);
                file.write(jsonA.toJSONString());
                file.flush();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            json.put("date", "" + d + "");
            json.put("wave", wave);
            try (FileWriter file = new FileWriter(fileName)){
                file.write(json.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean checkFileContentExists(String fileName) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            return br.readLine() != null;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void jsonFileTest() throws IOException, InterruptedException {
        dataStore(new Date(), 5, "Highscore.json");
        Thread.sleep(1000);
        dataStore(new Date(), 10, "Highscore.json");
    }
}
