package ru.kfu.itis;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mg on 14.02.15.
 */
public class WorkWithWords {

    public static WorkWithWords workWithWords = new WorkWithWords();
    private ArrayList<String> listOfRussianWords;
    private ArrayList<String> listOfFiveCharactersWords;

    private WorkWithWords(){
        File fileWithWords = new File("/home/mg/Загрузки/Balda/web/Words.txt");
        listOfRussianWords = new ArrayList<String>();
        listOfFiveCharactersWords = new ArrayList<String>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileWithWords));
            long t = (new Date()).getTime();
            String word = br.readLine();
            while(word != null){
//                System.out.println(word);
                listOfRussianWords.add(word);
                if(word.length() == 5){
                    listOfFiveCharactersWords.add(word);
//                    System.out.println(word);
                }
                word = br.readLine();
            }
            br.close();
            System.out.println((new Date()).getTime() - t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomFiveLengthWord(){
        int num = (int)(Math.random()*listOfFiveCharactersWords.size());
        return listOfFiveCharactersWords.get(num);
    }

    public boolean doesWordExist(String word){
        boolean returning = false;
        if(listOfRussianWords.contains(word)){
            returning = true;
        }
        return returning;
    }







}
