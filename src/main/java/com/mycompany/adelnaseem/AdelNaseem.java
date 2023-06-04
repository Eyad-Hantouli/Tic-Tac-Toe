/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.adelnaseem;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author acer
 */
public class AdelNaseem {

    // checking for winner
    static int winningCheck (char[] posArr) {
        int winner = 0;

        // top-left position check.
        if (
                posArr[0] == posArr[1] && posArr[1] == posArr[2] ||
                posArr[0] == posArr[3] && posArr[3] == posArr[6] ||
                posArr[0] == posArr[4] && posArr[4] == posArr[8]
        ) {
            if (posArr[0] == 'X') {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }

        // middle position check.
        if (
                posArr[3] == posArr[4] && posArr[4] == posArr[5] ||
                posArr[1] == posArr[4] && posArr[4] == posArr[7] ||
                posArr[2] == posArr[4] && posArr[4] == posArr[6]
        ) {
            if (posArr[4] == 'X') {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }

        // bottom-right position check.
        if (
                posArr[6] == posArr[7] && posArr[7] == posArr[8] ||
                posArr[2] == posArr[5] && posArr[5] == posArr[8]
        ) {
            if (posArr[8] == 'X') {
                winner = 1;
            }
            else {
                winner = 2;
            }
        }

        return winner;
    }
    static String getBoard (char[] posArr) {
        
        String board = "\t" + posArr[0] + "\t|\t" + posArr[1] + "\t|\t" + posArr[2]+ "\n" + "-".repeat(48) + "\n"
               +"\t" + posArr[3] + "\t|\t" + posArr[4] + "\t|\t" + posArr[5]+ "\n" + "-".repeat(48) + "\n"
               +"\t" + posArr[6] + "\t|\t" + posArr[7] + "\t|\t" + posArr[8]+ "\n";
        
        return board;
    }

    public static void main(String[] args) {
        char[] posArr = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
        Set<Integer> posSet = new HashSet<>();
        String board;
        int pickedPosCounter = 0;
        board = getBoard(posArr);
        Scanner playerPos = new Scanner(System.in);
        Random botPos = new Random();
        int currentPos;

        int winner = 0;
        String finishStatement = "Draw";

        System.out.println("Here's the board numbered from 0 - 8.");
        System.out.println(board);
        System.out.println("You are the \'X\' boy.");
        
        while (pickedPosCounter < 9 && winner == 0) {
            
            // Player (Human) Choice
            
            System.out.print("Select position 0-8: ");
            currentPos = (int) playerPos.nextInt();
            System.out.println("");
            
            while (posSet.contains(currentPos) || currentPos < 0 || currentPos > 8) {
                System.out.print("This position has already been taken. Select another one: ");
                currentPos = (int) playerPos.nextInt();
                System.out.println("");
            }
            
            posSet.add(currentPos);
            pickedPosCounter++;
            posArr[currentPos] = 'X';
            board = getBoard(posArr);

            // checking for winner

            winner = winningCheck(posArr);
            if (pickedPosCounter >= 9 || winner != 0) {
                break;
            }
            // Bot Choice
            
            currentPos = botPos.nextInt(8 - 0 + 1);
            
            System.out.println("Bot making decision...");
            while (posSet.contains(currentPos) || currentPos < 0 || currentPos > 8) {
                currentPos = botPos.nextInt(8 - 0 + 1);
            }
            
            posSet.add(currentPos);
            pickedPosCounter++;
            posArr[currentPos] = 'O';
            board = getBoard(posArr);
            
            System.out.println(board);
            // checking for winner
            winner = winningCheck(posArr);
        }
        // checking for winner
        winner = winningCheck(posArr);
        
        System.out.println(board);

        if (winner == 1) {
            finishStatement = "Winner Winner Cheken Dinner";
        }
        else if (winner == 2) {
            finishStatement = "Bot Wins";
        }
        finishStatement = finishStatement.concat(" !!");

        System.out.println(finishStatement);
    }
}
