package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.shape.CircleShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddWaterTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.core.engine.pipeline.RandomSpotPondered;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class Rain implements HydrologyStep<RainConfig> {

    @Override
    public void apply(World world, RainConfig config) {

        for (int i = 0; i < config.getCount(); i++) {
            Coordinates center = new RandomSpotPondered(world).getCoords();
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
