package net.frankie.uselessmod.entity.custom;

import net.frankie.uselessmod.blocks.ModBlocks;
import net.frankie.uselessmod.blocks.custom.DiceBlock;
import net.frankie.uselessmod.entity.ModEntities;
import net.frankie.uselessmod.item.ModItems;
import net.frankie.uselessmod.item.custom.GoldenFakeSwordItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class GoldenSwordProjectileEntity extends ThrowableItemProjectile{
    public GoldenSwordProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public GoldenSwordProjectileEntity(Level pLevel) {
        super(ModEntities.GOLDEN_SWORD_PROJECTILE.get(), pLevel);
    }
    public GoldenSwordProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.GOLDEN_SWORD_PROJECTILE.get(), livingEntity, pLevel);
    }


    @Override
    protected Item getDefaultItem() {
        return ModItems.GOLDEN_FAKE_SWORD.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof LivingEntity ? 5 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), i);
        this.discard();
    }


}
