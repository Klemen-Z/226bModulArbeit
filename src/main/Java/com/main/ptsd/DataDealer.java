package com.main.ptsd;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Date;

public class DataDealer {
    private String fileName;
    JSONParser jsonP = new JSONParser();

    DataDealer(String fileName){
        setFileName(fileName);
        File f = new File(fileName);
        assert f.exists();
        assert f.isFile();
    }

    @SuppressWarnings("unchecked")
    public void dataStore(Date d, Integer wave) throws IOException {
        JSONObject json = new JSONObject();
        JSONObject finalO = new JSONObject();
        JSONArray jsonA = new JSONArray();
        FileWriter file = new FileWriter(fileName);
        json.put("date", "" + d + "");
        json.put("wave", wave);
        if (checkFileContentExists()){
            try {
                jsonA.addAll(JSONFileParser());
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
    public boolean checkFileContentExists() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            return br.readLine() != null;
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }
    @SuppressWarnings("unchecked")
    public JSONArray JSONFileParser() throws IOException, ParseException {
        JSONArray jsonA = new JSONArray();
        JSONArray o = (JSONArray) jsonP.parse(new FileReader(fileName));
        jsonA.addAll(o);
        return jsonA;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
