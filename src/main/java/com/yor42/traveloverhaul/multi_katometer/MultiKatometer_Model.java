package com.yor42.traveloverhaul.multi_katometer;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MultiKatometer_Model extends AnimatedGeoModel<MultiKatometer_item>{

    @Override
    public ResourceLocation getModelLocation(MultiKatometer_item object) {
        return new ResourceLocation(Constants.MODID, "geo/multi_katometer.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MultiKatometer_item object) {
        return new ResourceLocation(Constants.MODID, "textures/items/multi_katometer.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MultiKatometer_item animatable) {
        return new ResourceLocation(Constants.MODID, "animations/katometer.animation.json");
    }
}
