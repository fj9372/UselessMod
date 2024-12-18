package net.frankie.uselessmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class iRunSwordItem extends SwordItem {
    public iRunSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int isSelected) {
        if (!level.isClientSide()) {
            if(player.getMainHandItem().getItem() == this){
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40, 2000, false, false, true));
            }
        }
        super.onInventoryTick(stack, level, player, slotIndex, isSelected);
    }
}
