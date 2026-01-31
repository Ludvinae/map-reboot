package com.yorkhuul.life.map.zone;

public class Tile {

    private float altitude;
    private float water;

    public Tile(float altitude) {
        setAltitude(altitude);
        setWater(0);
    }

    // Getters
    public float getAltitude() {
        return altitude;
    }

    public float getWater() {
        return water;
    }


    // Setters
    public void setAltitude(float altitude) {
        this.altitude = this.clamp(altitude);
    }

    public void setWater(float water) {
        if (water < 0) water = 0;
        this.water = water;
    }


    // Others
    @Override
    public String toString() {
        return "Tile altitude: " + this.getAltitude();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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

    public void addWater(float value) {
        setWater(getWater() + value);
    }

}
