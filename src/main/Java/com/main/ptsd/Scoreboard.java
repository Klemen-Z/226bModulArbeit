package com.main.ptsd;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Scoreboard {
    Map<LocalDateTime, Integer> scoreboard = new TreeMap<>();

    public void addEntry(int wave, LocalDateTime time){
        scoreboard.put(time, wave);
    }
    public void removeEntry(LocalDateTime key){
        scoreboard.remove(key);
    }
    public Integer[] getScoreboard(int size){
        if(size > 0){
            Integer[] topScores = new Integer[size - 1];
            for (int i = 0; i < size; i++){
                topScores[i] = 0;
            }
            for (Integer score1 : scoreboard.values()){
                for (int i = size - 1; i == 0; i--){
                    if (i == 0){
                        topScores[i] = score1;
                    } else if(topScores[i + 1] < topScores[i]){
                    } else if(topScores[i] < score1){
                        topScores[i] = score1;
                    }
                }
            }
        }
        return null;
    }
}
