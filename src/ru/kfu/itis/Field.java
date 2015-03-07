package ru.kfu.itis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by mg on 14.02.15.
 */
public class Field implements Serializable{

    private char[][] fieldMatrix;
    private ArrayList<String> listOfPlayersWords;
    private int lastX;
    private int lastY;

    Field(){
        fieldMatrix = new char[5][5];

    }

    Field(String firstWord){
        fieldMatrix = new char[5][5];
        listOfPlayersWords = new ArrayList<String>();
        listOfPlayersWords.add(firstWord);
        if(firstWord.length() == 5){
            firstWord.toUpperCase();
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 5; j++){
                    fieldMatrix[i][j] = ' ';
                }

            }

            for(int i = 0; i < 5; i++){
                fieldMatrix[2][i] = firstWord.charAt(i);
            }

            for(int i = 3; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    fieldMatrix[i][j] = ' ';
                }
            }
        }
    }

    public boolean isWordInWasList(String word){
        return listOfPlayersWords.contains(word.toUpperCase());
    }

    private boolean checkWord(String word){
        boolean returning = false;
        char checkingCharacter = word.charAt(0);

        ArrayList<Coordinats> coordinatsOfAllWordCharacters = new ArrayList<Coordinats>();
        for(int i = 0; i < 5 && !returning; i++){
            for(int j = 0; j < 5 && !returning; j++){
                if(fieldMatrix[i][j] == checkingCharacter){
                    coordinatsOfAllWordCharacters.add(new Coordinats(i, j));
                    returning = checkWord(word.substring(1), coordinatsOfAllWordCharacters);
                    coordinatsOfAllWordCharacters.remove(0);
                }
            }
        }
        return returning;
    }

    private boolean contains(ArrayList<Coordinats> list, Coordinats element){
        boolean returning = false;
        for(int i = 0; i < list.size() && !returning; i++){
            if(list.get(i).getX()==element.getX() && list.get(i).getY()==element.getY()){
                returning = true;
            }
        }
        return returning;
    }

    private boolean checkWord(String word, ArrayList<Coordinats> listOfWordCharactersCoordinats){
        boolean returning = false;
        if(word.equals("")){
            if(contains(listOfWordCharactersCoordinats, new Coordinats(lastX, lastY))) {
                returning = true;
            }
        }else{
            char checkingCharacter = word.charAt(0);
            Coordinats current = listOfWordCharactersCoordinats.get(listOfWordCharactersCoordinats.size() - 1);
            int x = current.getX();
            int y = current.getY();

            if(!contains(listOfWordCharactersCoordinats, new Coordinats(x - 1, y))
                    && getTopCharacterFor(x, y) == checkingCharacter){
                listOfWordCharactersCoordinats.add(new Coordinats(x - 1, y));
                returning = checkWord(word.substring(1), listOfWordCharactersCoordinats);
                listOfWordCharactersCoordinats.remove(listOfWordCharactersCoordinats.size() - 1);
            }

            if(!contains(listOfWordCharactersCoordinats,new Coordinats(x, y + 1))
                    && !returning
                    && getRightCharacterFor(x, y) == checkingCharacter){
                listOfWordCharactersCoordinats.add(new Coordinats(x, y + 1));
                returning = checkWord(word.substring(1), listOfWordCharactersCoordinats);
                listOfWordCharactersCoordinats.remove(listOfWordCharactersCoordinats.size() - 1);
            }

            if(!contains(listOfWordCharactersCoordinats,new Coordinats(x + 1, y))
                    && !returning
                    && getBottomCharacterFor(x, y) == checkingCharacter){
                listOfWordCharactersCoordinats.add(new Coordinats(x + 1, y));
                returning = checkWord(word.substring(1), listOfWordCharactersCoordinats);
                listOfWordCharactersCoordinats.remove(listOfWordCharactersCoordinats.size() - 1);
            }

            if(!contains(listOfWordCharactersCoordinats,new Coordinats(x, y - 1))
                    && !returning
                    && getLeftCharacterFor(x, y) == checkingCharacter){
                listOfWordCharactersCoordinats.add(new Coordinats(x, y - 1));
                returning = checkWord(word.substring(1), listOfWordCharactersCoordinats);
                listOfWordCharactersCoordinats.remove(listOfWordCharactersCoordinats.size() - 1);
            }
        }
        return returning;
    }

    public boolean addChar(int xPosition, int yPosition, char addingCharacter, String word) throws InputCharacterException {
        boolean returning = false;
        if(xPosition>=0 && xPosition<=5 && yPosition>=0 && yPosition <=5 && (addingCharacter + "").matches("\\p{L}")){
            lastX = xPosition;
            lastY = yPosition;
            fieldMatrix[xPosition][yPosition] = addingCharacter;
            returning = checkWord(word);
        }else{
            throw new InputCharacterException();
        }
        if(!returning){
            fieldMatrix[lastX][lastY] = ' ';
        }else{
            listOfPlayersWords.add(word);
        }
        return returning;
    }

    public char getChar(int xPosition, int yPosition) throws InputCharacterException {
        char ret = ' ';
        if(xPosition>=0 && xPosition<=4 && yPosition>=0 && yPosition <=4){
            ret = fieldMatrix[xPosition][yPosition];
        }else{
            throw new InputCharacterException();
        }
        return ret;
    }




    private char getTopCharacterFor(int xPosition, int yPosition){
        char returning = ' ';
        if(xPosition - 1 >= 0){
//            System.out.println("t " + fieldMatrix[xPosition - 1][yPosition]);
            returning = fieldMatrix[xPosition - 1][yPosition];
        }
        return returning;
    }

    private char getRightCharacterFor(int xPosition, int yPosition){
        char returning = ' ';
        if(yPosition + 1 <=4){
//            System.out.println("r " + fieldMatrix[xPosition][yPosition + 1]);
            returning = fieldMatrix[xPosition][yPosition + 1];
        }
        return returning;
    }

    private char getBottomCharacterFor(int xPosition, int yPosition){
        char returning = ' ';
        if(xPosition + 1 <= 4){
//            System.out.println("b " + fieldMatrix[xPosition + 1][yPosition]);
            returning = fieldMatrix[xPosition + 1][yPosition];
        }
        return returning;
    }

    private char getLeftCharacterFor(int xPosition, int yPosition){
        char returning = ' ';
        if(yPosition - 1 >= 0){
//            System.out.println("l " + fieldMatrix[xPosition][yPosition - 1]);
            returning = fieldMatrix[xPosition][yPosition - 1];
        }
        return returning;
    }

    public boolean isEnvironmentEmpty(int xPosition, int yPosition){
        boolean returning = true;
        if(getBottomCharacterFor(xPosition, yPosition) == ' '
                && getLeftCharacterFor(xPosition, yPosition) == ' '
                && getRightCharacterFor(xPosition, yPosition) == ' '
                && getTopCharacterFor(xPosition, yPosition) == ' '){
            returning = false;

        }
        return returning;
    }

    public void removeLast(){
        fieldMatrix[lastX][lastY] = ' ';
    }

    public boolean isFieldFull(){
//        boolean returning = true;
//        for(int i = 0; i < 5 && returning; i++){
//            for(int j = 0; j < 5 && returning; j++){
//                if(fieldMatrix[i][j] == ' '){
//                    returning = false;
//                }
//            }
//        }
//        return returning;
        return (listOfPlayersWords.size() >= 21)?true:false;
    }

}
