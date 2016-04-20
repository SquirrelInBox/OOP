package com.company;

import java.util.Map;

/**
 * Created by днс on 25.02.2015.
 */
public abstract class DictMap {
    public abstract void addWord(String word);
    public abstract Map<String, Integer> getWords(String prefix, int quant);
    public void writeDict(Map<String, Integer> dict){
        for(Map.Entry e:dict.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}