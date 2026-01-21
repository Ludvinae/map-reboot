package com.yorkhuul.life.map;

import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.generator.Volcanic;
import com.yorkhuul.life.map.generator.WorldGenerator;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World();
        System.out.println(gaia);
        System.out.println(gaia.getRegion(1, 1));
        System.out.println(gaia.getRegion(1, 1).getTile(1, 1));

        List<GenerationStep> steps = List.of(new Volcanic());
        WorldGenerator generator = new WorldGenerator(steps);
    }
}
