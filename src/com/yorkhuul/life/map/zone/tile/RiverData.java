package com.yorkhuul.life.map.zone.tile;

public class RiverData {

    private float cumulativeFlow;
    private float width;
    private TileWithCoordinates outlet = null;


    public RiverData(float flow) {
        this.cumulativeFlow = flow;
    }

    public float getCumulativeFlow() {
        return cumulativeFlow;
    }

    public float getWidth(){
        return width;
    }

    public TileWithCoordinates getOutlet() {
        return outlet;
    }

    public void setOutlet(TileWithCoordinates outlet) {
        this.outlet = outlet;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setCumulativeFlow(float cumulativeFlow) {
        this.cumulativeFlow = cumulativeFlow;
    }

    @Override
    public String toString() {
        return "River with flow of " + cumulativeFlow + " and width of " + width;
    }

    public float computeNormalizedFlow(float maxFlow) {
        return cumulativeFlow / maxFlow;
    }


    public void addFlow(float flow) {
        this.cumulativeFlow = getCumulativeFlow() + flow;
    }
}
