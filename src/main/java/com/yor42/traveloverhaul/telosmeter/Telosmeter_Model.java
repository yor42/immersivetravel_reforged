package com.yor42.traveloverhaul.telosmeter;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Telosmeter_Model extends AnimatedGeoModel<Telosmeter_item>{

    @Override
    public ResourceLocation getModelLocation(Telosmeter_item object) {
        return new ResourceLocation(Constants.MODID, "geo/telosmeter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Telosmeter_item object) {
        return new ResourceLocation(Constants.MODID, "textures/items/telosmeter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Telosmeter_item animatable) {
        return new ResourceLocation(Constants.MODID, "animations/telosmeter.animation.json");
    }
}
