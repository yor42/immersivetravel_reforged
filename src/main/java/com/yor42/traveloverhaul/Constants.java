package com.yor42.traveloverhaul;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class Constants {
    public static final String MODID = "traveloverhaul";

    public static void PrintSealevel(Player playerEntity, int seaLevel){
        if(seaLevel < 0)
        {
            if (seaLevel == -1)
            {
                playerEntity.sendMessage(new TranslatableComponent("text_belowsealvl1", Math.abs(seaLevel)), UUID.randomUUID());

            }else {playerEntity.sendMessage(new TranslatableComponent("text_belowsealvl", Math.abs(seaLevel)), UUID.randomUUID()); }

        }else if (seaLevel > 0)
        {
            if (seaLevel == 1)
            {
                playerEntity.sendMessage(new TranslatableComponent("text_abovesealvl1", Math.abs(seaLevel)), UUID.randomUUID());

            }else{playerEntity.sendMessage(new TranslatableComponent("text_abovesealvl", Math.abs(seaLevel)), UUID.randomUUID()); }

        }else{playerEntity.sendMessage(new TranslatableComponent("text_sealevel"), UUID.randomUUID());}
    }

    public static void printcoordandaltitude(Player playerEntity, int X, int Z, int seaLevel){
        if(seaLevel < 0)
        {
            if (seaLevel == -1)
            {

                playerEntity.sendMessage(new TranslatableComponent("text_position_belowsealevel1", X, Z, Math.abs(seaLevel)), UUID.randomUUID());

            }else {playerEntity.sendMessage(new TranslatableComponent("text_position_belowsealevel", X, Z, Math.abs(seaLevel)), UUID.randomUUID()); }

        }else if (seaLevel > 0)
        {
            if (seaLevel == 1)
            {
                playerEntity.sendMessage(new TranslatableComponent("text_position_abovesealevel1", X, Z, Math.abs(seaLevel)), UUID.randomUUID());

            }else{playerEntity.sendMessage(new TranslatableComponent("text_position_abovesealevel", X, Z, Math.abs(seaLevel)), UUID.randomUUID()); }

        }else{playerEntity.sendMessage(new TranslatableComponent("text_position_atsealevel", X, Z), UUID.randomUUID());}
    }

    public static void PrintXZ(Player player, int X, int Z){
        player.sendMessage(new TranslatableComponent("text_myposition", X,Z), UUID.randomUUID());
    }
}
