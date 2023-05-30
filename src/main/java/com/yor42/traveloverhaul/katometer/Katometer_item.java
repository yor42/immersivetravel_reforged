package com.yor42.traveloverhaul.katometer;

import com.yor42.traveloverhaul.Constants;
import com.yor42.traveloverhaul.telosmeter.Telosmeter_Renderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
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
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class Katometer_item extends Item implements IAnimatable {
    AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public Katometer_item(Item.Properties settings) {
        super(settings);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 25;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("katometer", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(net.minecraft.world.level.Level world, Player playerEntity, InteractionHand hand) {
        if (world.dimension() == net.minecraft.world.level.Level.NETHER) {
            playerEntity.startUsingItem(hand);
            return InteractionResultHolder.consume(playerEntity.getItemInHand(hand));
        }
        return ItemStack.EMPTY.use(world, playerEntity, hand);
    }


    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level world, LivingEntity user) {
        Player playerEntity = user.getCommandSenderWorld().getNearestPlayer(user, 1);
        if(playerEntity==null){
            return stack;
        }
        int X = playerEntity.getOnPos().getX();
        int Z = playerEntity.getOnPos().getZ();
        if (world.isClientSide && world.dimension() == Level.NETHER) {
            playerEntity.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
            Constants.PrintXZ(playerEntity, X, Z);
        }
        return stack;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new Katometer_Renderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(new TranslatableComponent("item.traveloverhaul.katometer.tooltip"));
    }

}
