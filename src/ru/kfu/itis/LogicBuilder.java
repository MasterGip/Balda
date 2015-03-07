package ru.kfu.itis;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mg on 19.02.15.
 */
public class LogicBuilder implements Serializable{
    private ArrayList<String>  listOfCurrentPlayers;
    private int numberOfPlayers;

    LogicBuilder(int number, String player_1) throws InputCharacterException {
        if(number < 2 || number > 10){
            throw new InputCharacterException();
        }
        numberOfPlayers = number;
        listOfCurrentPlayers = new ArrayList<String>();
        listOfCurrentPlayers.add(player_1);
    }

    public boolean isGameReady(){
        boolean returning = false;
        if(listOfCurrentPlayers.size() == numberOfPlayers){
            returning = true;
        }
        return returning;
    }

    public int readyPlayers(){
        return listOfCurrentPlayers.size();
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public boolean addPlayer(String player){
        boolean returning = isPlayerInList(player);
        if(!returning){
            listOfCurrentPlayers.add(player);
        }
        return !returning;
    }

    public boolean isPlayerInList(String player){
        boolean returning = false;
        if(listOfCurrentPlayers.contains(player)){
            returning = true;
        }
        return returning;
    }

    public Logic getLogic(){
        Logic returning = null;
        if(isGameReady()){
            try {
                returning = new Logic(listOfCurrentPlayers);
            } catch (InputCharacterException e) {
                e.printStackTrace();
                deleteAllSameNames();
            }
        }
        return returning;
    }

    private void deleteAllSameNames(){
        for(int i = 0; i < listOfCurrentPlayers.size(); i++){
            for(int j = i + 1; j < listOfCurrentPlayers.size(); j++){
                if(listOfCurrentPlayers.get(i).equals(listOfCurrentPlayers.get(j))){
                    listOfCurrentPlayers.remove(j);
                }
            }
        }
    }
}
