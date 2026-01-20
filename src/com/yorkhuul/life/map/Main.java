package com.yorkhuul.life.map;

import com.yorkhuul.life.map.zone.World;

public class Main {
    static void main() {
        World gaia = new World();
        System.out.println(gaia);
        System.out.println(gaia.getRegion(1, 1));
        System.out.println(gaia.getRegion(1, 1).getTile(1, 1));
    }
}
