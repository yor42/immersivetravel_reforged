package com.yor42.traveloverhaul.items;

import com.yor42.traveloverhaul.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.lang.Math;


import java.util.List;
import java.util.UUID;

public class Sextant_item extends Item{



    public Sextant_item(Item.Properties settings) {
        super(settings);
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 24000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand Hand) {
        playerEntity.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
        return ItemUtils.startUsingInstantly(world, playerEntity, Hand);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity playerentity, int p_41415_) {
        Player player = playerentity.getCommandSenderWorld().getNearestPlayer(playerentity, 1);
        if (world.dimension() == Level.OVERWORLD) //check if world = to overworld
        {
            if(!world.isClientSide){
                return;
            }
            int X = playerentity.getOnPos().getX();
            int Z = playerentity.getOnPos().getZ();
            double SkyAng = world.getSunAngle(0); //get the angle of the sun in radians
            float Pitch = playerentity.getXRot();
            float Yaw = MathHelper.wrapDegrees(playerentity.getYRot());//get the angle based on the f3 menu

            //convert sun angle to degrees and then round
            SkyAng = Math.round(Math.toDegrees(SkyAng));
            double SkyAngN = (SkyAng + 180); //sky angle for night time
            if (SkyAngN > 360) {
                SkyAngN = SkyAngN % 360;
            }
            //logic for turning pitch into a full 360 rotation
            if (Yaw > 0) {
                Pitch = Math.round(Pitch) + 90;
            }
            if (Yaw < 0) {
                //pretty sure this can be 1 statement
                Pitch = Pitch - 90;
                Pitch = Math.abs(Pitch);
                Pitch = Pitch + 180;
                Pitch = Math.round(Pitch); //weird way of basically multiplying it by -1 and adding 90, I was sleep-deprived ok
            }
            Yaw = Math.round(Yaw);
            //checking if player is looking at sun with 2 degrees of error
            if (Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 ||
                    Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2) {
                if (world.isClientSide) //isClient to make sure server doesn't try to run sendMessage, which causes a crash
                {

                    player.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                    Constants.PrintXZ(player,X,Z);
                }
            } else {
                player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
            }
        } else {
            player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(new TranslatableComponent("item.traveloverhaul.sextant.tooltip"));
    }
}