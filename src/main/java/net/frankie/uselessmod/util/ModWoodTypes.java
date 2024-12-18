package net.frankie.uselessmod.util;

import net.frankie.uselessmod.UselessMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType PINE = WoodType.register(new WoodType(UselessMod.MOD_ID + ":pine", BlockSetType.OAK));
}
