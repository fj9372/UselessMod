package net.frankie.uselessmod.item;

import net.frankie.uselessmod.UselessMod;
import net.frankie.uselessmod.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UselessMod.MOD_ID);

    public static void register(IEventBus eventBus){
        CREATE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> uselessmod_TAB = CREATE_MODE_TABS.register("uselessmod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.uselessmod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SAPPHIRE.get());
                        pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                        pOutput.accept(ModItems.STRAWBERRY.get());
                        pOutput.accept(ModItems.PINE_CONE.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_STAIRS.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_SLAB.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_BUTTON.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_FENCE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_WALL.get());

                        pOutput.accept(ModBlocks.SAPPHIRE_DOOR.get());
                        pOutput.accept(ModBlocks.SAPPHIRE_TRAPDOOR.get());
                        pOutput.accept(ModItems.SAPPHIRE_STAFF.get());

                        pOutput.accept(ModItems.SAPPHIRE_AXE.get());
                        pOutput.accept(ModItems.SAPPHIRE_HOE.get());
                        pOutput.accept(ModItems.SAPPHIRE_SHOVEL.get());
                        pOutput.accept(ModItems.SAPPHIRE_PICKAXE.get());

                        pOutput.accept(ModItems.SAPPHIRE_HELMET.get());
                        pOutput.accept(ModItems.SAPPHIRE_CHESTPLATE.get());
                        pOutput.accept(ModItems.SAPPHIRE_LEGGINGS.get());
                        pOutput.accept(ModItems.SAPPHIRE_BOOTS.get());

                        pOutput.accept(ModItems.STRAWBERRY_SEEDS.get());
                        pOutput.accept(ModItems.CORN_SEEDS.get());
                        pOutput.accept(ModItems.CORN.get());

                        pOutput.accept(ModBlocks.CATMINT.get());

                        pOutput.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());
                        pOutput.accept(ModItems.RHINO_SPAWN_EGG.get());
                        pOutput.accept(ModBlocks.GEM_POLISHING_STATION.get());
                        pOutput.accept(ModBlocks.PINE_LEAVES.get());
                        pOutput.accept(ModBlocks.PINE_WOOD.get());
                        pOutput.accept(ModBlocks.PINE_LOG.get());
                        pOutput.accept(ModBlocks.PINE_PLANKS.get());
                        pOutput.accept(ModBlocks.STRIPPED_PINE_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_PINE_WOOD.get());

                        pOutput.accept(ModItems.PINE_SIGN.get());
                        pOutput.accept(ModItems.PINE_HANGING_SIGN.get());
                        pOutput.accept(ModItems.PINE_CHEST_BOAT.get());
                        pOutput.accept(ModItems.PINE_BOAT.get());
                        pOutput.accept(ModItems.DICE.get());
                        pOutput.accept(ModBlocks.PINE_SAPLING.get());

                        pOutput.accept(ModItems.WOULDNT_SWORD.get());
                        pOutput.accept(ModItems.STONED_SWORD.get());
                        pOutput.accept(ModItems.IRUN_SWORD.get());
                        pOutput.accept(ModItems.GOLDEN_FAKE_SWORD.get());
                        pOutput.accept(ModItems.DIE_MEND_SWORD.get());

                    })
                    .build());
}
