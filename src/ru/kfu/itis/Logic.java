package ru.kfu.itis;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by mg on 14.02.15.
 */
public class Logic implements Serializable {
    private Field field;
    private ArrayList<Gamer> players;
    private Gamer playerNow;


    public Logic(){

    }

    public Logic(Logic logic){
        this.field = logic.field;
        this.players = logic.players;
        this.playerNow = logic.playerNow;
    }

    public void assignLogic(Logic logic){
        this.field = logic.field;
        this.players = logic.players;
        this.playerNow = logic.playerNow;
    }

    public Logic(ArrayList<String> listOfNames) throws InputCharacterException {
        boolean checkForDifferentNames = true;

        ArrayList<Coordinats> coors = new ArrayList<Coordinats>();
        coors.add(new Coordinats(1, 1));
//        System.out.println("!" + WorkWithWords.workWithWords.doesWordExist("РАК"));
        //Проверяем имена на различие
        for(int i = 0; i < listOfNames .size() && checkForDifferentNames; i++){
            String currentName = listOfNames.get(i);
            for(int j = i + 1; j < listOfNames.size() && checkForDifferentNames; j++){
                if(currentName.equals(listOfNames.get(j))){
                    checkForDifferentNames = false;
                }
            }
        }
        if(!checkForDifferentNames){
            throw new InputCharacterException();
        }
        String firstWord = WorkWithWords.workWithWords.getRandomFiveLengthWord().toUpperCase();
        field = new Field(firstWord);

        players = new ArrayList<Gamer>();
        for(int i = 0; i < listOfNames.size(); i++){

            players.add(new Gamer(listOfNames.get(i)));
        }
        playerNow = players.get(0);

    }


    public String getCharacter(int xPosition, int yPosition){
        String returning = "";
        try {
            returning = field.getChar(xPosition, yPosition) + "";
        } catch (InputCharacterException e) {
            e.printStackTrace();
        }
        return returning;
    }

    public boolean addCharacter(int xPosition, int yPosition, String character, String word){
        boolean returning = false;
        character = character.toUpperCase();
        if(field.isEnvironmentEmpty(xPosition, yPosition) && word.length()>0
                && !field.isWordInWasList(word)
                && WorkWithWords.workWithWords.doesWordExist(word)){
            try {
                returning = field.addChar(xPosition, yPosition, character.charAt(0), word);
            } catch (InputCharacterException e) {
                e.printStackTrace();
            }
        }
        if(returning){

            playerNow.addWord(word);
            playerNow.addPoints(word.length());
            int indexOfCurrentPlayer = (players.indexOf(playerNow))+1;
            if(indexOfCurrentPlayer == players.size()){
                indexOfCurrentPlayer = 0;
            }
            playerNow = players.get(indexOfCurrentPlayer);
        }
        return returning;
    }

    public String getTextField(int xPosition, int yPosition){
        String returning = "";

        try {
            if(field.getChar(xPosition, yPosition) == ' '){
                returning = "<input type=\"text\" maxlength=\"1\" name=\"field_" + xPosition + "_" + yPosition + "\">";
            }else{
                returning = "<input type=\"text\" maxlength=\"1\" name=\"field_" + xPosition + "_" + yPosition + "\" " +
                        "value=\"" + field.getChar(xPosition, yPosition) + "\" disabled>";
            }
        } catch (InputCharacterException e) {
            e.printStackTrace();
        }

        return returning;
    }

    public ArrayList<String> getPlayerList(){
        ArrayList<String> returning = new ArrayList<String>();
        for(int i = 0; i < players.size(); i++){
            String name = players.get(i).getName();
            if(players.get(i).equals(playerNow)){
                name = "<b>" + name + "</b>";
            }
            returning.add(name);
        }
        return returning;
    }

    public int getPointsOfPlayer(int numberOfPlayer){
        return players.get(numberOfPlayer).getPoints();
    }

    public int getNumberOfWords(){
        return playerNow.getListOfWords().size();
    }

    public String getWord(int player, int numberOfWord){
        return players.get(player).getListOfWords().get(numberOfWord);
    }

    public boolean isPlayerCurrentPlayer(int numberOfPlayer){
        boolean returning = false;
//        System.out.println(playerNow.getName() + " " + players.get(numberOfPlayer).getName());
        if(playerNow == players.get(numberOfPlayer)){
//            System.out.println("true");
            returning = true;
        }
        return returning;
    }

    public boolean isFieldFull(){

        return field.isFieldFull();
    }

    public void sortPlayers(){

        Collections.sort(players);
    }

    public Logic getLogicFromFile(String file){
        Logic logic = null;
        File inputFile = new File(file);
        if (!inputFile.exists()){
            try {
                inputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fi = new FileInputStream(inputFile);
            ObjectInputStream stream = new ObjectInputStream(fi);
                logic = (Logic) stream.readObject();
//            System.out.println(logic.players);
//            assignLogic(logic);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return logic;

    }

    public void saveLogic(String file){
        File outFile = new File(file);

            try {

                FileOutputStream fo = new FileOutputStream(file);
                ObjectOutputStream stream = new ObjectOutputStream(fo);
                stream.writeObject(this);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    public void deleteLogic(String file){
        File outFile = new File(file);

        try {

            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream stream = new ObjectOutputStream(fo);
            stream.writeObject(null);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
