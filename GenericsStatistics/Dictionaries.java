package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by днс on 22.02.2015.
 */
public abstract class Dictionaries {
    class Structure{
        String word;
        int count;
        public Structure(String word, int count)
        {
            this.word = word;
            this.count = count;
        }
    }
    abstract void addWord(String Word);
    abstract List<Structure> getWords(String prefix, int quant);
}
