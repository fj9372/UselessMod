package net.frankie.uselessmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DieMendSwordItem extends SwordItem {
    private int chargeLevel = 0;

    public DieMendSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {
        if (!level.isClientSide()) {
            if(player.getMainHandItem().getItem() == this && this.getDamage(stack) > 0){
                player.hurt(player.damageSources().playerAttack(player), 2);
                this.setDamage(stack, this.getDamage(stack) - 5);
            }
        }
        super.onInventoryTick(stack, level, player, slotIndex, selectedIndex);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!player.level().isClientSide) {
            entity.hurt(player.damageSources().playerAttack(player), 2);
            player.sendSystemMessage(Component.literal("Durability:" + (this.getMaxDamage(stack) - this.getDamage(stack))));
            player.sendSystemMessage(Component.literal("Strength:" + player.getAttackStrengthScale(0.0F)));
            player.sendSystemMessage(Component.literal("??:" + chargeLevel));
            entity.hurt(player.damageSources().playerAttack(player), ((float) Math.pow(2, chargeLevel)));
            stack.hurtAndBreak(156 * chargeLevel, player, (target) -> {
                target.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
            chargeLevel = 0;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide) {
            pPlayer.startUsingItem(pUsedHand);
            return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
        }
        return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pLivingEntity instanceof Player player) {
            pLevel.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ARROW_SHOOT, SoundSource.BLOCKS, 1f, 1f, 0);
        }
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player player && !pLevel.isClientSide) {
            if(pRemainingUseDuration % 20 == 0 && chargeLevel < 5){
                pLevel.playSeededSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1f, 1f, 0);
                chargeLevel += 1;
            }
            pLivingEntity.sendSystemMessage(Component.literal("Remaining:" + pRemainingUseDuration));
            pLivingEntity.sendSystemMessage(Component.literal("Power:" + chargeLevel));
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }
}
