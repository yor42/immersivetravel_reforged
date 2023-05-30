package com.yor42.traveloverhaul.katometer;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Katometer_Renderer extends GeoItemRenderer<Katometer_item>
{
    public Katometer_Renderer()
    {
        super(new Katometer_Model());
    }
}
