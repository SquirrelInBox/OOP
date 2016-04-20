package com.company;

import java.util.ArrayList;

public class DictArrayList extends Dictionaries {
    public ArrayList<Structure> dictionary;
    private int lengthDict;

    public DictArrayList()
    {
        dictionary = new ArrayList<Structure>();
        lengthDict = 0;
    }

    private void addNewEl(String newWord)
    {
        int pos = 0;
        while (pos < lengthDict && newWord.compareToIgnoreCase(dictionary.get(pos).word) > 0)
            pos ++;
        dictionary.add(pos, new Structure(newWord, 1));
        lengthDict ++;
    }

    public void addWord(String word)
    {
        if (word.length() < 4) {
            return;
        }
        for (int i = 0; i < lengthDict; i++)
        {
            Structure tempEl = dictionary.get(i);
            if ((tempEl.word).equals(word))
            {
                tempEl.count ++;
                return;
            }
        }
        addNewEl(word);
    }

    private void changeElements(ArrayList<Structure> dict, int i, int j, Structure firstEl, Structure secondEl)
    {
        dict.set(i, secondEl);
        dict.set(j, firstEl);
    }

    private void sortedDict(ArrayList<Structure> dict)
    {
        int len = dict.size();
        int i = 0;
        while (i < len) {
            Structure firstEl = dict.get(i);
            int j = i + 1;
            while (j < len){
                Structure secondEl = dict.get(j);
                if (secondEl.count > firstEl.count){
                    changeElements(dict, i, j, firstEl, secondEl);
                    break;
                }
                if (secondEl.count == firstEl.count && secondEl.word.compareToIgnoreCase(firstEl.word) < 0)
                {
                    changeElements(dict, i, j, firstEl, secondEl);
                    break;
                }
                j++;
            }
            i++;
        }
    }

    public ArrayList<Structure> getWords(String prefix, int quant)
    {
        ArrayList<Structure> result = new ArrayList<Structure>(quant);
        int i = 0;
        int count = 0;
        while (i < lengthDict && count < quant)
        {
            Structure tempWord = dictionary.get(i);
            if (tempWord.word.startsWith(prefix))
            {
                result.add(new Structure(tempWord.word, tempWord.count));
                count++;
            }
            i++;
        }
        sortedDict(result);
        writeDict(result);
        return result;
    }

    public void writeDict(ArrayList<Structure> dict)
    {
        for (int i = 0; i < dict.size(); i++)
            System.out.println(dict.get(i).word + ' ' + dict.get(i).count);
    }
}
