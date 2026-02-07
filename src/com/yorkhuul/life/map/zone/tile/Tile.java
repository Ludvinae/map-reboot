package com.yorkhuul.life.map.zone.tile;

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
