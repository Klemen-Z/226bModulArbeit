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
    public void dataStore(Date d, Integer Score) throws IOException {
        boolean b = false;
        JSONObject json = new JSONObject();
        JSONObject finalO = new JSONObject();
        JSONArray jsonA = new JSONArray();
        FileWriter file = new FileWriter(fileName);
        json.put("date", "" + d + "");
        json.put("score", Score);
        try {
            JSONArray j = JSONFileArrayParser();
            jsonA.addAll(j);
            jsonA.add(json);
            finalO.put("Highscores", jsonA);
            file.write(finalO.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            b = true;
        }
        if (b){
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
    public JSONArray JSONFileArrayParser(){
        JSONArray jsonA = new JSONArray();
        try{
            FileReader reader = new FileReader(fileName);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject)parser.parse(reader);
            jsonA = (JSONArray)object.get("Highscores");
            return jsonA;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonA;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
