package com.yor42.traveloverhaul.viator;

import com.yor42.traveloverhaul.telosmeter.Telosmeter_Renderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import static com.yor42.traveloverhaul.Constants.printcoordandaltitude;

public class Viator_item extends Item implements IAnimatable, ISyncable {
    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final String controllerName = "useController";
    public static final int ANIM = 0;
    public static final int ANIM_STOP = 1;

    public Viator_item(Item.Properties settings) {
        super(settings);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 30;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.BLOCK;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data)
    {
        @SuppressWarnings({ "unchecked", "rawtypes" })
        AnimationController<Viator_item> controller = new AnimationController(this, controllerName, 1,
                this::predicate);

        data.addAnimationController(controller);

    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        if (!world.isClientSide) {
            playerEntity.startUsingItem(hand);
            final int id = GeckoLibUtil.guaranteeIDForStack(playerEntity.getItemInHand(hand), (ServerLevel) world);
            GeckoLibNetwork.syncAnimation(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(()->playerEntity), this, id, ANIM);
        }
        return InteractionResultHolder.consume(playerEntity.getItemInHand(hand));
    }


    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        Player playerEntity = user.getCommandSenderWorld().getNearestPlayer(user, 1);
        int X = playerEntity.getOnPos().getX();
        int Z = playerEntity.getOnPos().getZ();
        int Y = playerEntity.getOnPos().getY();
        if (world.isClientSide) {
            playerEntity.playSound(SoundEvents.BOOK_PAGE_TURN, 1.0F, 1.0F);
            int seaLevel = Y - 63;
            printcoordandaltitude(playerEntity, X, Z, seaLevel);
        }
        return stack;
    }


    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity playerentity, int remainingUseTicks) {
        Player playerEntity = playerentity.getCommandSenderWorld().getNearestPlayer(playerentity, 1);
        final InteractionHand hand = playerEntity.getUsedItemHand();
        if (!world.isClientSide) {
            final int id = GeckoLibUtil.guaranteeIDForStack(playerEntity.getItemInHand(hand), (ServerLevel) world);
            GeckoLibNetwork.syncAnimation(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(()->playerEntity), this, id, ANIM_STOP);
        }
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new Viator_Renderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

    @SuppressWarnings("resource")
    @Override
    public void onAnimationSync(int id, int state) {
        @SuppressWarnings("rawtypes")
        final AnimationController controller = GeckoLibUtil.getControllerForID(this.factory, id, controllerName);
        if (state == ANIM_STOP) {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("stop", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        }
        if (state == ANIM) {
                controller.markNeedsReload();
                controller.setAnimation(new AnimationBuilder().addAnimation("use", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(new TranslatableComponent("item.traveloverhaul.viator.tooltip"));
    }

}