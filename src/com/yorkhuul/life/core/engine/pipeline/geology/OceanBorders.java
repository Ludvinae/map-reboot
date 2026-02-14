package com.yorkhuul.life.core.engine.pipeline.geology;

import com.yorkhuul.life.core.engine.parameters.FloatParameter;
import com.yorkhuul.life.core.engine.parameters.IntParameter;
import com.yorkhuul.life.core.engine.parameters.Parameter;
import com.yorkhuul.life.core.engine.shape.MapEdges;
import com.yorkhuul.life.core.engine.shape.Shape;
import com.yorkhuul.life.core.engine.shape.effect.AddEffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.EffectTarget;
import com.yorkhuul.life.core.engine.shape.effect.ShapeEffect;
import com.yorkhuul.life.core.engine.pipeline.GenerationStep;
import com.yorkhuul.life.core.world.World;

import java.util.ArrayList;
import java.util.List;

public class OceanBorders implements GenerationStep<BorderConfig> {

    @Override
    public void apply(World world, BorderConfig config) {
        Shape edges = new MapEdges(
                world.getWidthInTiles(),
                world.getHeightInTiles(),
                config.getCoastWidth(),
                config.getStrength()
        );
        EffectTarget effect = new AddEffectTarget();
        ShapeEffect ocean = new ShapeEffect(edges, effect);

        world.applyShapeEffect(ocean);
        //consoleFeedback("Sea border");
    }


    @Override
    public String getName() {
        return "Ocean borders";
    }

    @Override
    public List<Parameter<?>> createParameters(BorderConfig config) {
        List<Parameter<?>> parameters = new ArrayList<>();

        parameters.add(new IntParameter("Water border width", 1, 500, config.getCoastWidth(), config::setCoastWidth));
        parameters.add(new FloatParameter("Effect strength",0.01f, 1f, config.getStrength(), 0.01f, config::setStrength));

        return parameters;
    }


}
