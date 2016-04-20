package com.company;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by днс on 22.02.2015.
 */
public class DictTreeMap extends DictMap {
    public TreeMap<String, Integer> dictionary;
    private int lengthDict;

    public DictTreeMap(){
        dictionary = new TreeMap<String, Integer>();
        lengthDict = 0;
    }

    public void addWord(String word) {
        if (word.length() < 4)
            return;
        for (String key: dictionary.keySet())
            if (key.equals(word)) {
                dictionary.put(word, dictionary.get(key) + 1);
                return;
            }
        dictionary.put(word, 1);
        lengthDict ++;
    }

    private void sortDict(Map<String, Integer> dict){

    }

    public Map<String, Integer> getWords(String prefix, int quant) {
        Map<String, Integer> result = new TreeMap<String, Integer>();
        int count = 0;
        for (String key: dictionary.keySet()){
            if (key.startsWith(prefix))
            {
                result.put(key, dictionary.get(key));
                count++;
            }
            if (count == quant)
                return result;
        }
        return result;
    }
}
