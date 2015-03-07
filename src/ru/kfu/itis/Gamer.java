package ru.kfu.itis;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mg on 14.02.15.
 */
public class Gamer implements Comparable<Gamer>, Serializable{
    private String name;
    private ArrayList<String> listOfWords;
    private int points;


    Gamer(String nick){
        name = nick;
        listOfWords = new ArrayList<String>();
        points = 0;

    }

    public void addWord(String word){
        listOfWords.add(word);
    }

    public void addPoints(int p){
        points += p;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getListOfWords() {
        return listOfWords;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListOfWords(ArrayList<String> listOfWords) {
        this.listOfWords = listOfWords;
    }


    @Override
    public int compareTo(Gamer o) {

        return -1 * ((Integer)this.getPoints()).compareTo(o.getPoints());
    }
}
