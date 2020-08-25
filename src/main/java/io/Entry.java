package io;

import java.util.Arrays;

public class Entry {
    public int[] numberSignature;
    public int[] suitSignature;
    public String number;
    public String suit;
    public int numberSum = 0;
    public int suitSum = 0;

    public Entry(int[] signature, int exitPoint) {
        this.numberSignature = Arrays.stream(signature).filter(x -> x < exitPoint).limit(23).toArray();
        this.suitSignature = Arrays.stream(signature).filter(x -> x < exitPoint).skip(23).toArray();
        if (suitSignature[0] < suitSignature[1]) {
            suitSignature[0] = suitSignature[1];
        }
        numberSum = getSumCounter(numberSignature);
        suitSum = getSumCounter(suitSignature);
        this.number = getNumber();
        this.suit = getSuit();
    }

    private int getSumCounter(int[] signature) {
        int min = getMin(signature);
        int counter = 0;
        for (int x : signature) {
            counter += x - min;
        }
        return counter;
    }

    private int getMin(int[] signature) {
        int min = Integer.MAX_VALUE;
        for (int x : signature) {
            if (min > x) {
                min = x;
            }
        }
        return min;
    }

    private boolean reduceFrom(int[] signature, int startIndex, int endIndex) {
        boolean reduce = true;
        int counter = 0;
        int prev = signature[0];
        for (int x : signature) {
            counter++;
            if (counter > startIndex && counter < endIndex) {
                reduce = reduce && prev >= x;
            }
            prev = x;
        }
        return reduce;
    }

    private String getNumber() {
        if (numberSum == 0) {
            return "K";
        } else if (numberSum > 149) {
            return "J";
        } else if (numberSum > 139) {
            return "4";
        } else if (reduceFrom(numberSignature,0, numberSignature.length)) {
            return "A";
        } else if (reduceFrom(numberSignature, 5, numberSignature.length)) {
            return "7";
        } else {
            return numberSignature[1] - numberSignature[8] <= 1 ?
                    (numberSignature[1] - numberSignature[8] >= 0 ?
                    (numberSum < 50 ?
                            "8" : "5") : (numberSignature[8] == numberSignature[16] ?
                            "10" : numberSignature[1] == numberSignature[3] ?
                            "3" : "2")) : remainderChecks();
        }
    }

    private String remainderChecks() {
        if (numberSignature[0] < numberSignature[16] || numberSignature[0] < numberSignature[17]) {
            return "9";
        } else if (numberSignature[5] == numberSignature[6] && numberSignature[6] == numberSignature[7]
                && numberSignature[7] == numberSignature[8] && numberSignature[8] == numberSignature[9]) {
            return "9";
        } else if (Math.abs(numberSignature[7] - numberSignature[11]) > 1) {
            return "8";
        } else {
            return numberSum > 40 ? "Q" : "6";
        }
    }

    private String getSuit() {
        boolean reduce = reduceFrom(suitSignature, 0, 14);
        if (reduce && (Math.abs(suitSignature[0] - suitSignature[9]) > 5)) {
            return "Peaks";
        } else if (reduce) {
            return "Clover";
        } else {
            return String.valueOf(suitSum);
        }
    }
}