package com.yorkhuul.life.map.zone;

public class Tile {

    private float altitude;
    private float flow;
    private Tile flowTarget;

    public Tile(float altitude) {
        setAltitude(altitude);
        setFlow(0);
        setFlowTarget(null);
    }

    // Getters
    public float getAltitude() {
        return altitude;
    }

    public float getFlow() {
        return flow;
    }

    public Tile getFlowTarget() {
        return flowTarget;
    }

    // Setters
    public void setAltitude(float altitude) {
        this.altitude = this.clamp(altitude);
    }

    public void setFlow(float flow) {
        if (flow < 0) flow = 0;
        else if (flow > 1) flow = 1;
        this.flow = flow;
    }

    public void setFlowTarget(Tile flowTarget) {
        this.flowTarget = flowTarget;
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
