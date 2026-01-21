package com.yorkhuul.life.map.tools;

import java.util.Random;

public class RandomInteger {

    private int min;
    private int max;
    private int randomInt;

    public RandomInteger(int min, int max) {
        Random random = new Random();
        setMin(min);
        setMax(max);
        this.randomInt = getRandom(random);
    }

    public RandomInteger(int max) {
        this(0, max);
    }

    // Getters
    public int getRandomInt() {
        return randomInt;
    }

    // Setters
    private void setMin(int min) {
        if (min < 0) {
            min = 0;
        }
        this.min = min;
    }

    public void setMax(int max) {
        if (max <= min) {
            System.out.println("Warning: max value must be higher than min value");
            max += 1;
        }
        this.max = max;
    }

    // Methods
    private int getRandom(Random random) {
        return random.nextInt(this.min, this.max);
    }
}
