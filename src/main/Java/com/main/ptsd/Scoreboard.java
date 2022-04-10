package com.main.ptsd;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Scoreboard {
    //scoreboard list of scores and when
    Map<Integer, Long> scoreboard = new TreeMap<>();

    Scoreboard(){
        try{
            insertValues();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //return highest score value
    public Long getHighscore(){
        Long t = 0L;
        for (Long i : scoreboard.values()){
            if (i > t){
                t = i;
            }
        }
        return t;
    }

    //print Entire scoreboard
    public void getList(){
        for (Long i : scoreboard.values()){
            System.out.println(i);
        }
    }

    //refresh values inside scoreboard
    public void insertValues() throws IOException {
        DataDealer d = new DataDealer("Highscore.json");
        scoreboard.clear();
        scoreboard.putAll(d.getValues());
    }
}
