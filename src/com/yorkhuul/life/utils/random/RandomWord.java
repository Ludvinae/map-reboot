package com.yorkhuul.life.utils.random;

import static java.awt.Color.yellow;

public class RandomWord {

    static String[] wordDictionnary = {
            "Anchor", "Autumn", "Beacon", "Breeze", "Bright", "Bridge", "Candle",
            "Canvas", "Castle", "Cedar", "Cherry", "Circle", "Cloud", "Coast",
            "Coral", "Cosmic", "Crystal", "Dancer", "Dawn", "Desert", "Drift",
            "Echo", "Ember", "Energy", "Feather", "Forest", "Garden", "Gentle",
            "Glacier", "Golden", "Harbor", "Harmony", "Haven", "Horizon", "Island",
            "Ivory", "Jungle", "Lantern", "Legend", "Meadow", "Memory", "Mirror",
            "Misty", "Mountain", "Nature", "Ocean", "Olive", "Orbit", "Palace", "Pebble",
            "Planet", "Prairie", "Quartz", "Quiet", "Radiant", "River", "Rocket",
            "Silver", "Simple", "Spirit", "Spring", "Starry", "Summit", "Sunset",
            "Timber", "Tranquil", "Travel", "Valley", "Velvet", "Vision", "Wander",
            "Whisper", "Willow", "Winter", "Wonder", "Zephyr", "Bloom", "Clover",
            "Daring", "Falcon", "Galaxy", "Grove", "Hunter", "Kindle", "Lagoon",
            "Marble", "Nectar", "Orchid", "Petal", "Ripple", "Sapphire", "Shadow",
            "Shimmer", "Skyline", "Stellar", "Sunrise", "Voyage"
    };

    public static String getRandomWord() {
        int index = (int) (Math.random() * wordDictionnary.length);
        return wordDictionnary[index];
    }
}
