package net.frankie.uselessmod.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class iRunSwordItem extends SwordItem {
    public iRunSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity){
            MobEffectInstance getBlindness = player.getEffect(MobEffects.BLINDNESS);
            if (getBlindness != null) {
                float damageTaken = ((LivingEntity) entity).getMaxHealth()/2;
                entity.hurt(player.damageSources().playerAttack(player), Math.min(20, damageTaken));
                stack.hurtAndBreak(((int) damageTaken), player, (target) -> {
                    target.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                });
            }
            else{
                Vec3 lookDirection = player.getLookAngle();
                Vec3 playerPosition = player.position();
                Vec3 dropPosition = playerPosition.add(lookDirection.x * -5, 0, lookDirection.z * -5);
                ItemEntity dropSword = new ItemEntity(player.level(), dropPosition.x, dropPosition.y, dropPosition.z, stack);
                player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                player.level().addFreshEntity(dropSword);
            }
            return true;
        }
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pUsedHand);
        pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 5, false,false, true));
        pPlayer.hurt(pPlayer.damageSources().generic(), 2.0f);
        return InteractionResultHolder.sidedSuccess($$3, pLevel.isClientSide());
    }
}
