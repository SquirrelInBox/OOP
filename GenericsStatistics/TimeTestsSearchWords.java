package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by днс on 27.02.2015.
 */
public class TimeTestsSearchWords {
    DictArrayList arrayDict;
    DictLinkedList linkedDict;
    DictHashMap hashDict;
    DictTreeMap treeDict;
    String[] text;

    public TimeTestsSearchWords(String text) {
        this.text = text.split(" ");
        arrayDict = new DictArrayList();
        linkedDict = new DictLinkedList();
        hashDict = new DictHashMap();
        treeDict = new DictTreeMap();
    }

    public long getWordsArrayList(String prefix, int count){

        for (String aText : text) arrayDict.addWord(aText);
        List<Dictionaries.Structure> arrStr = new ArrayList<Dictionaries.Structure>();
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrStr = arrayDict.getWords(prefix, count);
        }
        long end = System.nanoTime();
        for (Dictionaries.Structure anarrStr:arrStr)System.out.println(anarrStr.word);
        return end - start;
    }

    public long getWordsLinkedList(String prefix, int count)
    {
        for (String aText : text) linkedDict.addWord(aText);
        List<Dictionaries.Structure> arrStr = new ArrayList<Dictionaries.Structure>();
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrStr = linkedDict.getWords(prefix, count);
        }
        long end = System.nanoTime();
        System.out.println(arrStr);
        return end - start;
    }

    public long getWordsHashMap(String prefix, int count)
    {
        for (String aText : text) hashDict.addWord(aText);
        Map<String, Integer> arrStr = hashDict.getWords(prefix, count);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrStr = hashDict.getWords(prefix, count);
        }
        long end = System.nanoTime();
        System.out.println(arrStr);
        return end - start;
    }

    public long getWordsTreeMap(String prefix, int count)
    {
        for (String aText : text) treeDict.addWord(aText);
        Map<String, Integer> arrStr = treeDict.getWords(prefix, count);
        long start = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            arrStr = treeDict.getWords(prefix, count);
        }
        long end = System.nanoTime();
        System.out.println(arrStr);
        return end - start;
    }
}
