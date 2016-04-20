package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("c:\\users\\днс\\Desktop\\Generics\\src\\harry.txt");

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        String text = "";
        while(dis.available() != 0)
            text += dis.readLine() + '\n';

        DictArrayList tempList = new DictArrayList();
        DictHashMap tempHashMap = new DictHashMap();

        String[] arrayStr = text.split(" |,|-|\\(|\\)|\\.");
//        for (String anArrayStr : arrayStr) {
//            tempList.addWord(anArrayStr);
//        }

//        tempList.getWords("Ha", 10);


        for (String anArrayStr:arrayStr) tempHashMap.addWord(anArrayStr);
        tempHashMap.getWords("Ha", 10);

//        TimeTestsAddWords tempList = new TimeTestsAddWords();
//        System.out.print(tempList.testArrayDictAddWords(text));
//        System.out.println(tempList.testLinkedDictAddWords(text));
//        System.out.println(tempList.testHashDictAddWords(text));
//        System.out.println(tempList.testTreeDictAddWords(text));

//        TimeTestsSearchWords tempSearch = new TimeTestsSearchWords(text);
//        System.out.println(tempSearch.getWordsArrayList("Ha", 1));
//        System.out.println(tempSearch.getWordsLinkedList("Ha", 1));
//        System.out.println(tempSearch.getWordsHashMap("Ha", 10));
//        System.out.println(tempSearch.getWordsTreeMap("Ha", 10));
//        System.out.println(text);
    }
}
