package com.main.ptsd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Date;

public class DataDealer {
    private String fileName;

    DataDealer(String fileName) throws IOException {
        boolean b;
        setFileName(fileName);
        File f = new File(fileName);
        if(!f.exists()){
            b = f.createNewFile();
            assert b;
        }
        assert f.isFile();
    }

    @SuppressWarnings("unchecked")
    public void dataStore(Date d, Integer Score){
        boolean b = false;
        JSONObject json = new JSONObject();
        JSONObject finalO = new JSONObject();
        JSONArray jsonA = new JSONArray();
        json.put("date", "" + d + "");
        json.put("score", Score);
        try {
            jsonA.addAll((JSONArray) JSONFileArrayParser().get("Highscores"));
            FileWriter file = new FileWriter(fileName);
            jsonA.add(json);
            finalO.put("Highscores", jsonA);
            file.write(finalO.toJSONString());
            file.flush();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            b = true;
        }
        if (b){
            try {
                FileWriter file = new FileWriter(fileName);
                jsonA.add(json);
                finalO.put("Highscores", jsonA);
                file.write(finalO.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public JSONObject JSONFileArrayParser() throws IOException, ParseException {
        FileReader reader = new FileReader(fileName);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject)parser.parse(reader);
        System.out.println("Object: " + json);
        return json;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
