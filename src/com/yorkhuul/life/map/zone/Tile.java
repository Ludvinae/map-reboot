package com.yorkhuul.life.map.zone;

public class Tile {

    private float altitude;

    public Tile(float altitude) {
        setAltitude(altitude);
    }

    // Getters
    public float getAltitude() {
        return altitude;
    }

    // Setters
    public void setAltitude(float altitude) {
        this.altitude = this.clamp(altitude);
    }

    // Others
    @Override
    public String toString() {
        return "Tile altitude: " + this.getAltitude();
    }

    // Methods
    public float clamp(float altitude) {
        if (altitude > 1) {
            return 1;
        }
        if (altitude < -1) {
            return -1;
        }
        return altitude;
    }

    public void add(float value) {
        setAltitude(getAltitude() + value);
    }

    public void multiply(float factor) {
        setAltitude(getAltitude() * factor);
    }
}
