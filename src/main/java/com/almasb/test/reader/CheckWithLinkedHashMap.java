package com.almasb.test.reader;

import java.io.*;
import java.net.URL;
import java.util.*;

public class CheckWithLinkedHashMap {

    List<Map.Entry<String, Integer>> parsedWordsColletion;
    HashMap<String,String> EnglishDict = new HashMap<>();
    public CheckWithLinkedHashMap(List<Map.Entry<String, Integer>> parsedWordsColletion) {
        this.parsedWordsColletion = parsedWordsColletion;
        //Load Dictinaty
        URL dictinaryFile = getClass().getResource("ENRUS.TXT");
        try (FileReader DictFile = new FileReader(dictinaryFile.getFile())){
            BufferedReader words = new BufferedReader(DictFile);

            String engWord;
            while ( (engWord = words.readLine()) != null){
                String rusWord = words.readLine();
                if (rusWord != null)
                    EnglishDict.put(engWord,rusWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TransletedHarryPotterWord> GetTransletedHarryPotterWords(){
        ArrayList<TransletedHarryPotterWord> TransletedHarryPotterWordList = new ArrayList<TransletedHarryPotterWord>();
        parsedWordsColletion.forEach((element) ->{
            TransletedHarryPotterWord test = new TransletedHarryPotterWord(element.getKey(), element.getValue(), EnglishDict.get(element.getValue()));
            TransletedHarryPotterWordList.add(test);
        });
        return TransletedHarryPotterWordList;
    }


}
