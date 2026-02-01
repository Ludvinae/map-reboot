package com.yorkhuul.life.map.features;

public class RiverData {

    private float flow;
    private float width;
    private float normalizedFlow;

    public RiverData(float flow, float width, float maxFlow) {
        this.flow = flow;
        this.width = width;
        computeNormalizedFlow(maxFlow);
    }

    public float getWidth(){
        return width;
    }

    public float getNormalizedFlow() {
        return normalizedFlow;
    }

    public void computeNormalizedFlow(float maxFlow) {
        normalizedFlow = flow / maxFlow;
    }
}
