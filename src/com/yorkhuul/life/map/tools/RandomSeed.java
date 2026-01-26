package com.yorkhuul.life.map.tools;

public class RandomSeed {


    public static int getRandomSeed(int n) {
        if (n < 3) n = 3;

        String alphaNumeric = "1234567890"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder builder = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumeric.length() * Math.random());


            builder.append(alphaNumeric.charAt(index));
        }
        int seed = builder.toString().hashCode();
        if (seed < 1) {
            seed = seed * -1;
        }

        return seed;
    }

    public static int getRandomSeed() {
        return getRandomSeed((int) (3 + Math.random() * 10));
    }

}
