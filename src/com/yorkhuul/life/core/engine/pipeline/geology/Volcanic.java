package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.engine.shape.CircleShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.utils.random.RandomSpot;
import com.yorkhuul.life.core.world.World;


public class Volcanic implements GenerationStep<VolcanicConfig> {

    @Override
    public void apply(World world, VolcanicConfig config) {
        int width = world.getWidthInTiles();
        int height = world.getHeightInTiles();

        for (int i = 0; i < config.getCount(); i++) {
            Coordinates coords = new RandomSpot(width, height).getCoords();

            int radius = new RandomInteger(config.getMinRadius(), config.getMaxRadius()).getRandomInt();
            Shape circle = new CircleShape(coords, radius, config.getStrength());
            EffectTarget effect = new AddEffectTarget();
            ShapeEffect volcanic = new ShapeEffect(circle, effect);

            world.applyShapeEffect(volcanic);
        }
        //consoleFeedback("Volcanic x " + count);
    }

    @Override
    public String getName() {
        return "Volcanic";
    }


}
