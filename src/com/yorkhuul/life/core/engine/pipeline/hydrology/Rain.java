package com.yorkhuul.life.core.engine.pipeline.hydrology;

import com.yorkhuul.life.core.engine.shape.CircleShape;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddWaterTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.utils.position.Coordinates;
import com.yorkhuul.life.utils.random.RandomInteger;
import com.yorkhuul.life.core.engine.pipeline.RandomSpotPondered;
import com.yorkhuul.life.core.world.World;

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

}
