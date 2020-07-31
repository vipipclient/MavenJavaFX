package com.almasb.test.reader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/*
* 1)Open file of Harry Potter book,copy the text of file in String in,close file
* 2)parse the tokens and write them in : StringTokenizer st
* 3)create HashTable without repetition ,for each word count the number of occurrences in the text
* 4)Sort the words alphabetically using TreeMap
* 5)HarryPotterWord class Sort words by the number of occurrences
* */
public class ReadText {
    private String textFileToParsePath;
    private String parsedWords;
    private  List<Map.Entry<String, Integer>> parsedWordsColletion;
    public String getParsedWords() {
        return parsedWords;
    }

    public List<Map.Entry<String, Integer>> getParsedWordsColletion() {
        return parsedWordsColletion;
    }

    public ReadText(String args[]){
        if (args.length<2){
            System.out.println("too few arguments");
            System.exit(1);
        }

        textFileToParsePath = args[0];
        int count = 0;
        int TotalWords = 0;
        String in = "";
        //1)open file with terxt of Harry Potter
        try (SeekableByteChannel fChan = Files.newByteChannel(Paths.get(textFileToParsePath)))
        {
            System.out.println("fChan.size()" + fChan.size());
            in =  new String(Files.readAllBytes(Paths.get(textFileToParsePath)));
        }catch (IOException e2) {System.out.println("Ошыбка ,братан: ");}

        //2)Tokenizer
        StringTokenizer st = new StringTokenizer(in," \r/,\\«.'~@#$%^_+{}[]><=&-*?!—:;`»()1234567890\n");

        //3)add all tokens to Hashtable without repetition ,for each word count the number of occurrences in the text
        Hashtable<String,Integer> Harry = new Hashtable<String,Integer>();
        Map<String,Integer>  HarryHashMap = new HashMap<String, Integer>();
        String a;//auxiliary variable
        int cnt;//number of repittition
        TotalWords = st.countTokens();
        while (st.hasMoreTokens()){
            a = st.nextToken().toLowerCase();
            if (Harry.containsKey(a)){
                cnt = Harry.get(a);
                HarryHashMap.put(a,cnt + 1);//<<<<<<<<<<<new realisation
                Harry.put(a,cnt + 1);//if the word is repeated , increase the counter
            }else{
                Harry.put(a,1); //else, create element
                HarryHashMap.put(a,1);//<<<<<<<<<<<new realisation
            }

        }
//    *****************************************************************
        List<Map.Entry<String, Integer>> sortedColletion = HarryHashMap.entrySet()
                .stream().sorted((t1, t2) -> {
                    return  ((t2.getValue() - t1.getValue()) == 0) ?
                            t1.getKey().compareTo(t2.getKey()) : (t2.getValue() - t1.getValue());
                }).collect(Collectors.toList());
        parsedWordsColletion = sortedColletion;
        System.out.println();
        System.out.println("Total words = " + TotalWords);
    }

    public Boolean saveToFile(String fileName){
        try (FileWriter fout = new FileWriter(fileName)){
            StringBuffer buf_SortedHarryHashMap = new StringBuffer();
            parsedWordsColletion.forEach((element) -> {
                buf_SortedHarryHashMap.append(String.format(element.getKey() + " " +
                        element.getValue() + " "));
            });
            parsedWords = buf_SortedHarryHashMap.toString();
            fout.write(parsedWords);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}


