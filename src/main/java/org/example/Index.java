package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Index {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Inser Username: ");
        String username = scan.nextLine();
        System.out.println("Welcome " + username + ", please insert password: ");
        String password = scan.nextLine();
    }

    static boolean listOfUsers(String username, String password) {
        String correctUsername = "Adam";
        String correctPassword = "Johnson";

        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            return true;
        }
        else {
            return false;
        }
    }
}