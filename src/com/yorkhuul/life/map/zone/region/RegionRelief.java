package com.yorkhuul.life.map.zone.region;

public enum RegionRelief {
    SEA(-1f, -0.15f),
    SHORE(-0.15f, -0.05f),
    BEACH(-0.05f, 0.05f),
    PLAIN(0.05f, 0.25f),
    HILLS(0.25f, 0.55f),
    MOUNTAINS(0.055f, 1f);

    private float minAltitude;
    private float maxAltitude;

    RegionRelief(float minAltitude, float maxAltitude) {
        this.minAltitude = minAltitude;
        this.maxAltitude = maxAltitude;
    }

    public float getMinAltitude() {
        return minAltitude;
    }

    public float getMaxAltitude() {
        return maxAltitude;
    }
}
