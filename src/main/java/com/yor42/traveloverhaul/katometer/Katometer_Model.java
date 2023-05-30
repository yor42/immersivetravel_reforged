package com.yor42.traveloverhaul.katometer;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Katometer_Model extends AnimatedGeoModel<Katometer_item>{

    @Override
    public ResourceLocation getModelLocation(Katometer_item object) {
        return new ResourceLocation(Constants.MODID, "geo/katometer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Katometer_item object) {
        return new ResourceLocation(Constants.MODID, "textures/items/katometer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Katometer_item animatable) {
        return new ResourceLocation(Constants.MODID, "animations/katometer.animation.json");
    }
}
