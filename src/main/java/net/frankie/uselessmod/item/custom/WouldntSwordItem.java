package net.frankie.uselessmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WouldntSwordItem extends SwordItem {

    public WouldntSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if(pTarget instanceof Zombie zombie && pAttacker instanceof Player player) {
            pAttacker.sendSystemMessage(Component.literal("Your sword has ditched you for the zombie."));
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            zombie.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.WOODEN_SWORD));
            zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 10, false,false, true));
            zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 5, false,false, true));
            zombie.addEffect(new MobEffectInstance(MobEffects.WITHER, 2000, 1, false,false, true));
            zombie.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
            zombie.setDropChance(EquipmentSlot.FEET, 100.0F);
            zombie.setDropChance(EquipmentSlot.CHEST, 100.0F);
            zombie.setDropChance(EquipmentSlot.LEGS, 100.0F);
            zombie.setDropChance(EquipmentSlot.HEAD, 100.0F);
            return false;
        }
        else if(pAttacker instanceof Player player){
            pAttacker.sendSystemMessage(Component.literal("Your sword attacked you and has ran away after giving the enemy a buff."));
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            player.hurt(player.damageSources().generic(), 5.0f);
            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000, 1, false,false, true));
            pTarget.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000, 1, false,false, true));
            return false;
        }
        else {
            return super.hurtEnemy(pStack, pTarget, pAttacker);
        }
    }
}
