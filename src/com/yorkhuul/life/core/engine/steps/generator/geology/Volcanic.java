package com.yorkhuul.life.core.engine.steps.generator.geology;

import com.yorkhuul.life.core.engine.config.geology.VolcanicConfig;
import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.shape.CircleShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.core.engine.steps.generator.GenerationStep;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.utils.random.RandomSpot;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Parameter<?>> createParameters(VolcanicConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 500, config.getCount(), config::setCount));
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, config.getMinRadius(), config::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, config.getMaxRadius(), config::setMaxRadius));
        parameters.add(new FloatParameter("Effect strength", 0.01f, 1f, config.getStrength(), 0.01f, config::setStrength));

        return parameters;
    }

}
