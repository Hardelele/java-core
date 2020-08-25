package io;

import java.util.Arrays;

public class Entry {
    public int[] numberSignature;
    public int[] suitSignature;
    public String number;
    public String suit;

    public Entry(int[] signature, int exitPoint) {
        this.numberSignature = Arrays.stream(signature).filter(x -> x < exitPoint).limit(23).toArray();
        this.suitSignature = Arrays.stream(signature).filter(x -> x < exitPoint).skip(23).toArray();
        this.number = getNumber();
        this.suit = getSuit();
    }

    private String getNumber() {
        int min = Integer.MAX_VALUE;
        for (int x : numberSignature) {
            if (min > x) {
                min = x;
            }
        }
        int counter = 0;
        int prev = numberSignature[0];
        boolean reduce = true;
        boolean seven = true;
        int index = 0;
        for (int x : numberSignature) {
            index++;
            counter += x - min;
            reduce = reduce && prev >= x;
            if (index > 5) {
                seven = seven && prev >= x;
            }
            prev = x;
        }
        if (counter == 0) {
            return "K";
        } else if (counter > 149) {
            return "J";
        } else if (counter > 139) {
            return "4";
        } else if (reduce) {
            return "A";
        } else if (seven) {
            return "7";
        } else if (numberSignature[1] - numberSignature[8] <= 1) {
            if (numberSignature[1] - numberSignature[8] >= 0) {
                return counter < 50 ? "8" : "5";
            } else {
                if (numberSignature[8] == numberSignature[16]) {
                    return "10";
                } else {
                    return numberSignature[1] == numberSignature[3] ? "3" : "2";
                }
            }
        } else if (numberSignature[0] < numberSignature[16] || numberSignature[0] < numberSignature[17]) {
            return "9";
        } else if (numberSignature[5] == numberSignature[6] && numberSignature[6] == numberSignature[7]
                && numberSignature[7] == numberSignature[8] && numberSignature[8] == numberSignature[9]) {
            return "9";
        } else if (Math.abs(numberSignature[7] - numberSignature[11]) > 1) {
            return "8";
        } else {
            return counter > 40 ? "Q" : "6";
        }
    }

    private String getSuit() {
        return null;
    }
}