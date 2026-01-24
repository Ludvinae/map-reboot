package com.yorkhuul.life.map.zone;

import com.yorkhuul.life.map.effect.ShapeEffect;
import com.yorkhuul.life.map.tools.BoundingBox;
import com.yorkhuul.life.map.tools.Coordinates;

public class Region {

    // determine la taille de la region et donc de la liste de tiles
    private static int size = 100;
    private final Tile[][] tiles;

    // coordonnées monde
    private final int regionX;
    private final int regionY;

    private String relief;

    public Region(int regionX, int regionY) {
        this.regionX = regionX;
        this.regionY = regionY;
        this.tiles = createTiles();
        this.relief = "sea";
    }

    // Getters
    public Tile getTile(int localX, int localY) {
        return tiles[localY][localX];
    }

    public static int getSize() {
        return size;
    }

    public String getRelief() {
        return this.relief;
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

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                result[y][x] = new Tile(0);
            }
        }
        return result;
    }

    // Calcul le relief en fonction de l'altitude des tiles à l'interieur
    public void calculRelief() {
        float[] data = browseRegion();
        float averageAlt = data[2];

        // very rough estimate for relief, wip
        if (averageAlt < -0.15) {
            this.relief = "sea";
        } else if (averageAlt <= 0) {
            this.relief = "shores";
        } else if (averageAlt <= 0.1) {
            this.relief = "beachs";
        } else if (averageAlt <= 0.25) {
            this.relief = "plains";
        } else if (averageAlt <= 0.5) {
            this.relief = "hills";
        } else {
            this.relief = "moutains";
        }
    }

    // Parcours les Tile et récupere les données statistiques
    private float[] browseRegion() {
        float minTile = 1;
        float maxTile = -1;
        float altitudeSum = 0;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                float altitude = this.getTile(y, x).getAltitude();
                altitudeSum += altitude;

                if (altitude < minTile) {
                    minTile = altitude;
                }
                if (altitude > maxTile) {
                    maxTile = altitude;
                }
            }
        }
        float[] data = new float[3];
        data[0] = minTile;
        data[1] = maxTile;
        data[2] = altitudeSum / (size * size);

        return data;
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
}
