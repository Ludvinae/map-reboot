package com.yorkhuul.life.map.zone;

import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.generator.Noise;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;

public class Region {

    // determine la taille de la region et donc de la liste de tiles
    private static int size = 100;
    private final Tile[][] tiles;

    // coordonnées monde
    private final int regionX;
    private final int regionY;

    private RegionRelief relief;
    private Noise noises;

    public Region(int regionX, int regionY) {
        this.regionX = regionX;
        this.regionY = regionY;
        noises = new Noise();
        this.tiles = createTiles();
        this.relief = RegionRelief.BEACH;

    }

    // Getters
    public Tile getTile(int localX, int localY) {
        return tiles[localY][localX];
    }

    public static int getSize() {
        return size;
    }

    public RegionRelief getRelief() {
        return relief;
    }

    // Setters
    // Bloque la taille des regions entre 10 et 1000 - entre 100 et 1 000 000 de tiles -
    public void setSize(int regionSize) {
        if (regionSize < 10) {
            size = 10;
        } else if (regionSize > 1000) {
            size = 1000;
        }
        else {
            size = regionSize;
        }
    }


    // Others
    @Override
    public String toString() {
        return relief + " at coordinates (" + regionY + ", " + regionX + ")";
    }

    // Helpers
    public Coordinates getLocalCoordinates() {
        return new Coordinates(regionX, regionY);
    }

    public Coordinates getWorldCoordinates(int x, int y) {
        return new Coordinates(regionX * size + x, regionY * size + y);
    }

    public BoundingBox getWorldBounds() {
        int x = regionX * size;
        int y = regionY * size;

        return new BoundingBox(x, y, x + size -1, y + size -1);
    }


    // Methods
    private Tile[][] createTiles() {
        Tile[][] result = new Tile[size][size];
        assert this.noises != null;
        float[][] noiseData = this.noises.generateNoiseData();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                result[y][x] = new Tile(0);
            }
        }
        return result;
    }

    // Calcul le relief en fonction de l'altitude des tiles à l'interieur
    public void calculRelief() {
        calculRelief(browseRegion());
    }

    public void calculRelief(RegionReliefData data) {
        float averageAlt = data.averageElevation();

        if (averageAlt < -0.15f) relief = RegionRelief.SEA;
        else if (averageAlt < -0.05f) relief = RegionRelief.SHORE;
        else if (averageAlt < 0.05f) relief = RegionRelief.BEACH;
        else if (averageAlt < 0.25f) relief = RegionRelief.PLAIN;
        else if (averageAlt < 0.55f) relief = RegionRelief.HILLS;
        else relief = RegionRelief.MOUNTAINS;

    }

    // Parcours les Tile et récupere les données statistiques
    private RegionReliefData browseRegion() {
        float minTile = 1;
        float maxTile = -1;
        float altitudeSum = 0;
        int numberImmerged = 0;
        int size = Region.getSize();
        int totalTiles = size * size;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                float altitude = this.getTile(x, y).getAltitude();
                altitudeSum += altitude;

                if (altitude < minTile) {
                    minTile = altitude;
                }
                if (altitude > maxTile) {
                    maxTile = altitude;
                }
                if (altitude > 0) {
                    numberImmerged += 1;
                }
            }
        }
        return new RegionReliefData(minTile, maxTile, altitudeSum / totalTiles, (float) numberImmerged / totalTiles);
    }

    public void applyShapeEffect(ShapeEffect effect) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                Coordinates coords = getWorldCoordinates(x, y);
                effect.applyTo(tiles[y][x], coords);
            }
        }
    }

    public void applyNoise(float strength) {
        for (Tile[] row : tiles) {
            for (Tile tile: row) {
                float influence = (float) (-strength + Math.random() * (strength * 2));
                tile.setAltitude(influence);
            }
        }
    }

    public void normalize(float strength) {
        RegionReliefData data = browseRegion();
        float min = data.minElevation();
        float max = data.maxElevation();

        float range = max - min;
        if (range == 0) return;

        calculRelief(data);

        float targetMin = relief.getMinAltitude();
        float targetMax = relief.getMaxAltitude();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Tile tile = getTile(j, i);
                float original = tile.getAltitude();

                float normalized = (tile.getAltitude() - min) / range;
                float remapped = targetMin + normalized * (targetMax - targetMin);

                float finalAltitude = original + strength * (remapped - original);
                tile.setAltitude(finalAltitude);

            }
        }
    }


}
