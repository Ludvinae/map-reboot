package com.yorkhuul.life.map.zone;

public class RiverData {

    private float flow;
    private float width;


    public RiverData(float flow) {
        this.flow = flow;
    }

    public float getFlow() {
        return flow;
    }

    public float getWidth(){
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "River with flow of " + flow + " and width of " + width;
    }

    public float computeNormalizedFlow(float maxFlow) {
        return flow / maxFlow;
    }

    public void addFlow(float flow) {
        this.flow = getFlow() + flow;
    }
}
