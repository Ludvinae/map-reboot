package com.yorkhuul.life.map.zone;

public class Tile {

    private float altitude;
    private float water; // hauteur de l'eau
    private float sediment;

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

    public float getSediment() {
        return sediment;
    }

    // Setters
    public void setAltitude(float altitude) {
        this.altitude = this.clamp(altitude);
    }

    public void setWater(float water) {
        if (water < 0) water = 0;
        this.water = water;
    }

    public void setSediment(float sediment) {
        this.sediment = sediment;
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
        setAltitude(getAltitude() + value);
    }

    public void multiplyAltitude(float factor) {
        setAltitude(getAltitude() * factor);
    }

    public void addWater(float value) {
        setWater(getWater() + value);
    }

    public void addSediment(float value) {
        setSediment(sediment + value);
    }

    /**
     * Total height of a tile with water taken into account
     * @return
     */
    public float waterSurface() {
        return altitude + water;
    }

}
