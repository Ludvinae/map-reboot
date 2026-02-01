package com.yorkhuul.life.map.steps.features;

public class RiverData {

    private float flow;
    private float width;
    private float normalizedFlow;

    public RiverData(float flow, float width, float maxFlow) {
        this.flow = flow;
        this.width = width;
        computeNormalizedFlow(maxFlow);
    }

    public float getFlow() {
        return flow;
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

    public void addFlow(float flow) {
        this.flow = getFlow() + flow;
    }
}
