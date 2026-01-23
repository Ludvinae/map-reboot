package com.yorkhuul.life.map;

import com.yorkhuul.life.display.WorldRenderer;
import com.yorkhuul.life.map.generator.GenerationStep;
import com.yorkhuul.life.map.generator.OceanBorders;
import com.yorkhuul.life.map.generator.Volcanic;
import com.yorkhuul.life.map.generator.WorldGenerator;
import com.yorkhuul.life.map.zone.World;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        World gaia = new World("gaia", 10, 10);
        System.out.println(gaia);


        List<GenerationStep> steps = List.of(new Volcanic(500, 10, 50, 0.5f),
                new OceanBorders(10, 1f));
        WorldGenerator generator = new WorldGenerator(steps);
        generator.generate(gaia);
        System.out.println(gaia.getRegion(5, 5));
        System.out.println(gaia.getRegion(5, 5).getTile(5, 5));
        System.out.println(gaia.getRegion(1, 1));
        System.out.println(gaia.getRegion(1, 1).getTile(1, 1));


        // Generation de l'image
        WorldRenderer render = new WorldRenderer(gaia);
        render.generateImage();
        render.exportImage();
    }
}
