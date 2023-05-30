package com.yor42.traveloverhaul;

import com.yor42.traveloverhaul.items.Barometer_item;
import com.yor42.traveloverhaul.items.Navigation_kit_item;
import com.yor42.traveloverhaul.items.Sextant_item;
import com.yor42.traveloverhaul.katometer.Katometer_item;
import com.yor42.traveloverhaul.multi_katometer.MultiKatometer_item;
import com.yor42.traveloverhaul.telosmeter.Telosmeter_item;
import com.yor42.traveloverhaul.viator.Viator_item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    public static final RegistryObject<Item> SEXTANT = register("sextant", ()->new Sextant_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> BAROMETER = register("barometer", ()->new Barometer_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> NAVIGATION_KIT = register("navigation_kit", ()->new Navigation_kit_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> KATOMETER = register("katometer", ()->new Katometer_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> MKATOMETER = register("multi_katometer", ()->new MultiKatometer_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> TELOSMETER = register("telosmeter", ()->new Telosmeter_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> VIATOR = register("viator", ()->new Viator_item(new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_TOOLS)));


    private static RegistryObject<Item> register(String id, Supplier<? extends Item> supplier){
        return ITEMS.register(id, supplier);
    }


}
