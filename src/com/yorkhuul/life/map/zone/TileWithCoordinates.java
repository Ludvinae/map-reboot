package com.yorkhuul.life.map.zone;

public class TileWithCoordinates {

    private Tile tile;
    private int worldX;
    private int worldY;

    private TileWithCoordinates lowestNeighbor;
    private float slope;
    private float flow;

    public TileWithCoordinates(Tile tile, int worldX, int worldY, TileWithCoordinates lowestNeighbor, float slope, float flow) {
        this.tile = tile;
        this.worldX = worldX;
        this.worldY = worldY;
        this.lowestNeighbor = lowestNeighbor;
        this.slope = slope;
        this.flow = flow;
    }

    public TileWithCoordinates(Tile tile, int worldX, int worldY) {
        this(tile, worldX, worldY, null, 0, 0);
    }

    public float getAltitude() {
        return tile.getAltitude();
    }

    public float getWater() {
        return tile.getWater();
    }

    public float getSediment() {
        return tile.getSediment();
    }

    public TileWithCoordinates getLowestNeighbor() {
        return lowestNeighbor;
    }

    public float getFlow() {
        return this.flow;
    }

    public float getSlope() {
        return slope;
    }

    public Tile getTile() {
        return tile;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setFlow(float flow) {
        if (flow < 0) flow = 0;
        if (flow > 1) flow = 1;
        this.flow = flow;
    }

    public void setSlope(float slope) {
        if (slope < 0) slope = 0;
        if (slope > 1) slope = 1;
        this.slope = slope;
    }

    public void setLowestNeighbor(TileWithCoordinates lowestNeighbor) {
        this.lowestNeighbor = lowestNeighbor;
    }

    public void addFlow(float flow) {
        setFlow(getFlow() + flow);
        }
}
