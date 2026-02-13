package com.yorkhuul.life.map.steps.generator.geology;

import com.yorkhuul.life.map.config.geology.VolcanicConfig;
import com.yorkhuul.life.map.parameters.FloatParameter;
import com.yorkhuul.life.map.parameters.IntParameter;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.shape.effect.AddEffectTarget;
import com.yorkhuul.life.map.shape.effect.EffectTarget;
import com.yorkhuul.life.map.shape.effect.ShapeEffect;
import com.yorkhuul.life.map.steps.generator.GenerationStep;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomSpot;

import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Volcanic implements GenerationStep<VolcanicConfig> {

    @Override
    public void apply(World world, VolcanicConfig config) {

        for (int i = 0; i < config.getCount(); i++) {
            Coordinates coords = new RandomSpot(world).getCoords();

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
