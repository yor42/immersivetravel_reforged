package com.yor42.traveloverhaul.mixins;

import com.yor42.traveloverhaul.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(Player.class)
public abstract class Spyglass extends LivingEntity {

    protected Spyglass(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Redirect(method = "isScoping", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean spyglass(ItemStack instance, Item item){
        if(this.isUsingItem() && this.getUseItem().is(ModItems.NAVIGATION_KIT.get())){
            return true;
        }
        if(this.isUsingItem() && this.getUseItem().is(ModItems.SEXTANT.get())){
            return true;
        }
        return this.isUsingItem() && this.getUseItem().is(Items.SPYGLASS);
    }

}

