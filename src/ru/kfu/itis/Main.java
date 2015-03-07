package ru.kfu.itis;

import sun.net.NetworkServer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mg on 15.02.15.
 */
public class Main {
    public static void main(String[] args) {

        Logic logic = new Logic();
        try {
            InetAddress.getByName("");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
