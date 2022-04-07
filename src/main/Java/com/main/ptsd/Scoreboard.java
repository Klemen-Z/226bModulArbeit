package com.main.ptsd;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Scoreboard {
    Map<Date, Integer> scoreboard = new TreeMap<>();

    Scoreboard(){

    }

    public void addEntry(int score, Date time){
        scoreboard.put(time, score);
    }
    public void removeEntry(Date key){
        scoreboard.remove(key);
    }
    public Integer getHighscore(){
        Integer t = 0;
        for (Integer i : scoreboard.values()){
            if (i > t){
                t = i;
            }
        }
        return t;
    }
    public void getList(){
        for (Integer i : scoreboard.values()){
            System.out.println(i);
        }
    }
}
