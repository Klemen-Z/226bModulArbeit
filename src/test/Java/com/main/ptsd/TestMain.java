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

        bob.getList();
        System.out.println("Highest: " + bob.getHighscore());
    }
    @SuppressWarnings("unchecked")
    public void dataStore(Date d, Integer wave, String fileName) throws IOException {
        JSONObject json = new JSONObject();
        JSONObject finalO = new JSONObject();
        JSONArray jsonA = new JSONArray();
        FileWriter file = new FileWriter(fileName);
        json.put("date", "" + d + "");
        json.put("wave", wave);
        if (checkFileContentExists(fileName)){
            try {
                jsonA.addAll(JSONFileParser(fileName));
                jsonA.add(json);
                finalO.put("Highscores", jsonA);
                file.write(finalO.toJSONString());
                file.flush();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                jsonA.add(json);
                finalO.put("Highscores", jsonA);
                file.write(finalO.toJSONString());
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
    @SuppressWarnings("unchecked")
    public JSONArray JSONFileParser(String fileName) throws IOException, ParseException {
        JSONArray jsonA = new JSONArray();
        JSONParser jsonP = new JSONParser();
        JSONArray o = (JSONArray) jsonP.parse(new FileReader(fileName));
        jsonA.addAll(o);
        return jsonA;
    }
    @Test
    public void jsonFileTest() throws IOException, InterruptedException {
        dataStore(new Date(), 5, "Highscore.json");
        Thread.sleep(1000);
        dataStore(new Date(), 10, "Highscore.json");
    }
}
