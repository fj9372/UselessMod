package net.frankie.uselessmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.frankie.uselessmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StonedSwordItem extends SwordItem {

    public StonedSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int isSelected) {
        if (!level.isClientSide()) {
            if(player.getMainHandItem().getItem() == this){
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 3, false, false, true));
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 1, false, false, true));
            }
        }
        super.onInventoryTick(stack, level, player, slotIndex, isSelected);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player player) {
            int stoneStacks = countFullStacksOfStone(player);
            float totalDamage = this.getDamage() + stoneStacks;
            pTarget.hurt(player.damageSources().playerAttack(player), totalDamage);
            consumeStacksOfStone(player);
        }

        pStack.hurtAndBreak(1, pAttacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });

        return true;
    }

    private int countFullStacksOfStone(Player player) {
        int fullStacks = 0;
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(Blocks.STONE.asItem()) && stack.getCount() == 64) {
                fullStacks++;
            }
        }
        return fullStacks;
    }

    private void consumeStacksOfStone(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(Blocks.STONE.asItem()) && stack.getCount() == 64) {
                stack.shrink(64);
            }
        }
    }
}

