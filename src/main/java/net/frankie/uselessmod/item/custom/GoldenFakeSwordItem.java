package net.frankie.uselessmod.item.custom;

import net.frankie.uselessmod.entity.custom.DiceProjectileEntity;
import net.frankie.uselessmod.entity.custom.GoldenSwordProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class GoldenFakeSwordItem extends SwordItem {
    private int timesThrown = 0;

    public GoldenFakeSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity){
            entity.hurt(player.damageSources().playerAttack(player), 2);
            stack.hurtAndBreak(10000, player, (target) -> {
                target.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
        }
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            GoldenSwordProjectileEntity $$4 = new GoldenSwordProjectileEntity(pLevel, pPlayer);
            $$4.setItem((timesThrown < 5) ? new ItemStack(Items.GOLD_INGOT) : new ItemStack(Items.STICK));
            $$4.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity($$4);
            $$3.hurtAndBreak(7, pPlayer, (target) -> {
                target.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
            timesThrown+=1;
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResultHolder.sidedSuccess($$3, pLevel.isClientSide());
    }
}
