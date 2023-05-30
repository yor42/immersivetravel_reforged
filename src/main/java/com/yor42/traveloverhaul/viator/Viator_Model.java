package com.yor42.traveloverhaul.viator;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Viator_Model extends AnimatedGeoModel<Viator_item>{

    @Override
    public ResourceLocation getModelLocation(Viator_item object) {
        return new ResourceLocation(Constants.MODID, "geo/viator.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Viator_item object) {
        return new ResourceLocation(Constants.MODID, "textures/items/viator.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Viator_item animatable) {
        return new ResourceLocation(Constants.MODID, "animations/viator.animation.json");
    }
}
