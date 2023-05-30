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

import java.util.List;
import java.util.UUID;

import static net.minecraft.world.level.Level.OVERWORLD;

public class Navigation_kit_item extends Item {

    public Navigation_kit_item(Item.Properties settings) {
        super(settings);
    }

    public int getUseDuration(ItemStack stack) {
        return 24000;
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        p_41433_.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
        p_41433_.startUsingItem(p_41434_);
        return InteractionResultHolder.consume(p_41433_.getItemInHand(p_41434_));
    }


    public void releaseUsing(ItemStack stack, Level world, LivingEntity playerentity, int remainingUseTicks) {
        Player player = playerentity.getCommandSenderWorld().getNearestPlayer(playerentity, 1);
        if(!world.isClientSide){
            return;
        }
        if (world.dimension() == OVERWORLD) {
            double SkyAng = world.getSunAngle(0);
            SkyAng = Math.toDegrees(SkyAng);
            SkyAng = Math.round(SkyAng);
            float Pitch = playerentity.getViewXRot(0);
            float Yaw = MathHelper.wrapDegrees(playerentity.getYRot());
            double SkyAngN = SkyAng + 180;
            if (SkyAngN > 360){
                SkyAngN= SkyAngN % 360;
            }
            if(Yaw > 0) {
                Pitch = Pitch + 90;
                Pitch = Math.round(Pitch);
            }
            if(Yaw < 0){
                Pitch = Pitch -90;
                Pitch = Math.abs(Pitch);
                Pitch = Pitch + 180;
                Pitch= Math.round(Pitch);
            }
            Yaw = Math.round(Yaw);
            if(Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAng - 2 && Pitch <= SkyAng + 2 ||
                    Yaw >= 88 && Yaw <= 92 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2 || Yaw >= -92 && Yaw <= -88 && Pitch >= SkyAngN - 2 && Pitch <= SkyAngN + 2) {
                int X = playerentity.getOnPos().getX();
                int Z = playerentity.getOnPos().getZ();
                int Y = playerentity.getOnPos().getY();
                player.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
                int seaLevel = Y - 63;
                Constants.printcoordandaltitude(player,X,Z,seaLevel);

            }else {player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);}
        }else {player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);}
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(new TranslatableComponent("item.traveloverhaul.navigation_kit.tooltip"));
    }

}