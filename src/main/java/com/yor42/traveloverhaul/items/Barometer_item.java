package com.yor42.traveloverhaul.items;

import java.lang.*;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class Barometer_item extends Item {

    public Barometer_item(Item.Properties settings) {
        super(settings);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 25;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        p_41433_.startUsingItem(p_41434_);
        return InteractionResultHolder.consume(p_41433_.getItemInHand(p_41434_));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        Player playerEntity = user.getCommandSenderWorld().getNearestPlayer(user, 1);

        if (world.isClientSide) {
            playerEntity.playSound(SoundEvents.LAVA_POP, 0.8F, 1.2F);
            playerEntity.playSound(SoundEvents.BOTTLE_FILL, 0.9F, 0.1F);
            int Y = playerEntity.getOnPos().getY();
            int seaLevel = Y - 63;
            Constants.PrintSealevel(playerEntity, seaLevel);
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world, List<Component> tooltip, @NotNull TooltipFlag tooltipContext) {
        tooltip.add(new TranslatableComponent("item.traveloverhaul.barometer.tooltip"));
    }
}
