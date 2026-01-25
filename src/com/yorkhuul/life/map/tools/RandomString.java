package com.yorkhuul.life.map.tools;

public class RandomString {


    public static int getRandomString(int n) {
        if (n < 3) n = 3;

        String alphaNumeric = "1234567890";

        StringBuilder builder = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumeric.length() * Math.random());

            builder.append(alphaNumeric.charAt(index));
        }
        return Integer.getInteger(builder.toString()) ;
    }

    public static int getRandomString() {
        return getRandomString((int) (3 + Math.random() * 10));
    }

}
