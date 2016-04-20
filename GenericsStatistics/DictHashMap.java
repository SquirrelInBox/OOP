package com.company;

import java.util.*;

/**
 * Created by днс on 22.02.2015.
 */
public class DictHashMap extends DictMap {
    public HashMap<String, Integer> dictionary;
    private int lengthDict;

    public DictHashMap(){
        dictionary = new HashMap<String, Integer>();
        lengthDict = 0;
    }

    public void addWord(String word){
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

    public void sortValues(Map<String, Integer> dict){
        ArrayList list = new ArrayList(dict.entrySet());
        Collections.sort(list, new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry e1, Map.Entry e2) {
                if (e1.getValue().equals(e2.getValue())){
                    return (e1.getKey().toString()).compareToIgnoreCase(e2.getKey().toString());
                }
                else {
                    if ((Integer) e1.getValue() < (Integer) e2.getValue())
                        return -1;
                    else
                        return 1;
                }
            }
        });
    }

    public Map<String, Integer> getWords(String prefix, int quant)
    {
        Map<String, Integer> result = new HashMap<String, Integer>(quant);
        int count = 0;
        for (String key: dictionary.keySet()){
            if (key.startsWith(prefix))
            {
                result.put(key, dictionary.get(key));
                count++;
            }
            if (count == quant) {
                writeDict(result);
//                sortValues(result);
                return result;
            }
        }
//        sortValues(result);
        writeDict(result);
        return result;
    }
}
