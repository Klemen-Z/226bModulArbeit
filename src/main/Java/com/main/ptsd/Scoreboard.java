package com.main.ptsd;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Scoreboard {
    Map<Integer, Long> scoreboard = new TreeMap<>();

    Scoreboard(){
        try{
            insertValues();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public Long getHighscore(){
        Long t = 0L;
        for (Long i : scoreboard.values()){
            if (i > t){
                t = i;
            }
        }
        return t;
    }
    public void getList(){
        for (Long i : scoreboard.values()){
            System.out.println(i);
        }
    }
    private void insertValues() throws IOException {
        DataDealer d = new DataDealer("Highscore.json");
        scoreboard.clear();
        scoreboard.putAll(d.getValues());
    }
}
