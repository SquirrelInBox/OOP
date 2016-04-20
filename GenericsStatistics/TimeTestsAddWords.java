package com.company;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by днс on 26.02.2015.
 */
public class TimeTestsAddWords {
    DictArrayList arrayDict;
    DictLinkedList linkedDict;
    DictHashMap hashDict;
    DictTreeMap treeDict;
    String p;

    public TimeTestsAddWords() {
        arrayDict = new DictArrayList();
        linkedDict = new DictLinkedList();
        hashDict = new DictHashMap();
        treeDict = new DictTreeMap();
        p = " |,|-|\\(|\\)|\\.";
    }

    private long oneTimeArrayDictAddWords(String[] arrayStr){
        long end = 0;
        long start = System.nanoTime();
        for (String anArrayStr : arrayStr) {
            arrayDict.addWord(anArrayStr);
        }
        end += System.nanoTime();
        return end - start;
    }

    public long testArrayDictAddWords(String text){
        long res = 0;
        String[] arrayStr = text.split(this.p);
//        for (String anarrayStr: arrayStr)System.out.println(anarrayStr);
        for (int i = 0; i < 10; i++)
            res += oneTimeArrayDictAddWords(arrayStr);
        return res / 10;
    }

    private long oneTimeLinkedDictAddWords(String text){
        String[] arrayStr = text.split(this.p);
        long end = 0;
        long start = System.nanoTime();
        for (String anArrayStr : arrayStr) {
            linkedDict.addWord(anArrayStr);
        }
        end += System.nanoTime();
        return end - start;
    }

    public long testLinkedDictAddWords(String text){ //время возвращается в микросекундах
        long res = 0;
        for (int i = 0; i < 10; i++)
            res += oneTimeLinkedDictAddWords(text);
        return res / 10;
    }

    private long oneTimeHashDictAddWords(String[] arrayStr){
        System.out.println(arrayStr.length);
        long end = 0;
        long start = System.nanoTime();
        for (String anArrayStr : arrayStr) {
            hashDict.addWord(anArrayStr);
        }
        end += System.nanoTime();
        return end - start;
    }

    public long testHashDictAddWords(String text)
    { //время возвращается в наносекундах
        long res = 0;
        String[] arrayStr = text.split(this.p);
        for (int i = 0; i < 10; i++)
            res += oneTimeHashDictAddWords(arrayStr);
        return res/10;
    }

    private long oneTimeTreeDictAddWords(String[] arrayStr){
        System.out.println(arrayStr.length);
        long end = 0;
        long start = System.nanoTime();
        for (String anArrayStr : arrayStr) {
            treeDict.addWord(anArrayStr);
        }
        end += System.nanoTime();
        return end - start;
    }

    public long testTreeDictAddWords(String text)
    { //время возвращается в наносекундах
        long res = 0;
        String[] arrayStr = text.split(this.p);
        for (int i = 0; i < 10; i++)
            res += oneTimeTreeDictAddWords(arrayStr);
        return res/10;
    }
}
