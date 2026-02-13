package com.yorkhuul.life.map.steps.generator.hydrology;

import com.yorkhuul.life.map.config.hydrology.RainConfig;
import com.yorkhuul.life.map.parameters.FloatParameter;
import com.yorkhuul.life.map.parameters.IntParameter;
import com.yorkhuul.life.map.parameters.Parameter;
import com.yorkhuul.life.map.context.EditorContext;
import com.yorkhuul.life.map.shape.effect.AddWaterTarget;
import com.yorkhuul.life.map.shape.effect.EffectTarget;
import com.yorkhuul.life.map.shape.effect.ShapeEffect;
import com.yorkhuul.life.map.shape.CircleShape;
import com.yorkhuul.life.map.shape.Shape;
import com.yorkhuul.life.map.tools.Coordinates;
import com.yorkhuul.life.map.tools.RandomInteger;
import com.yorkhuul.life.map.tools.RandomRainSpot;
import com.yorkhuul.life.map.zone.world.World;

import java.util.ArrayList;
import java.util.List;

public class Rain implements HydrologyStep<RainConfig> {

    @Override
    public void apply(World world, RainConfig config) {

        for (int i = 0; i < config.getCount(); i++) {
            Coordinates center = new RandomRainSpot(world).getCoords();
            int radius = new RandomInteger(config.getMinRadius(), config.getMaxRadius()).getRandomInt();

            float rainfall = (float) (Math.random() * config.getRainfallAmount());

            Shape circle = new CircleShape(center, radius, rainfall);
            EffectTarget target = new AddWaterTarget();
            ShapeEffect rain = new ShapeEffect(circle, target);

            world.applyShapeEffect(rain);
        }
        //consoleFeedback("Rain x " + count);
    }

    @Override
    public String getName() {
        return "Rain";
    }

    @Override
    public List<Parameter<?>> createParameters(RainConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Iterations count", 1, 100, config.getCount(), config::setCount));
        parameters.add(new IntParameter("Minimum influence radius", 1, 100, config.getMinRadius(), config::setMinRadius));
        parameters.add(new IntParameter("Maximum influence radius", 1, 100, config.getMaxRadius(), config::setMaxRadius));
        parameters.add(new FloatParameter("Rainfall amount", 0.01f, 1f, config.getRainfallAmount(), 0.01f, config::setRainfallAmount));

        return parameters;
    }

}
