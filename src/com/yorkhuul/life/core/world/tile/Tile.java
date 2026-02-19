package com.yorkhuul.life.core.world.tile;

public class Tile {

    private float altitude;
    private float baseTemp;

    public Tile(float altitude) {
        setAltitude(altitude);
    }

    // Getters
    public float getAltitude() {
        return altitude;
    }

    public float getBaseTemp() {
        return baseTemp;
    }

    // Setters
    public void setAltitude(float altitude) {
        this.altitude = this.clamp(altitude);
    }

    public void setBaseTemp(float baseTemp) {
        this.baseTemp = baseTemp;
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

    public void addAltitude(float value) {
        setAltitude(clamp(getAltitude() + value));
    }

    public void multiplyAltitude(float factor) {
        setAltitude(clamp(getAltitude() * factor));
    }

}
