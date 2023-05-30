package com.yor42.traveloverhaul.telosmeter;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class Telosmeter_Renderer extends GeoItemRenderer<Telosmeter_item>
{
    public Telosmeter_Renderer() {super(new Telosmeter_Model());}
}
