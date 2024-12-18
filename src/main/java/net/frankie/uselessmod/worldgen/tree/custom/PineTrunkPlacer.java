package net.frankie.uselessmod.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.frankie.uselessmod.worldgen.tree.ModTrunkPlacerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PineTrunkPlacer extends TrunkPlacer {
    public static final Codec<PineTrunkPlacer> CODEC = RecordCodecBuilder.create(pineTrunkPlacerInstance ->
            trunkPlacerParts(pineTrunkPlacerInstance).apply(pineTrunkPlacerInstance, PineTrunkPlacer::new));

    public PineTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerType.PINE_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int i, BlockPos blockPos, TreeConfiguration treeConfiguration) {
        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);
        int height = i + randomSource.nextInt(heightRandA, heightRandA+3) + randomSource.nextInt(heightRandB -1, heightRandB + 1);

        for(int j = 0; j < height; j++){
            placeLog(levelSimulatedReader, biConsumer, randomSource,blockPos.above(j), treeConfiguration);

            if(i % 2 == 0 && randomSource.nextBoolean()){
                if(randomSource.nextFloat() > 0.25f) {
                    for(int k = 0; k < 4; k++){
                        biConsumer.accept(blockPos.above(j).relative(Direction.NORTH, k), ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource,blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                }
            }

            if(i % 2 == 0 && randomSource.nextBoolean()){
                if(randomSource.nextFloat() > 0.25f) {
                    for(int k = 0; k < 4; k++){
                        biConsumer.accept(blockPos.above(j).relative(Direction.SOUTH, k), ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource,blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
                    }
                }
            }

            if(i % 2 == 0 && randomSource.nextBoolean()){
                if(randomSource.nextFloat() > 0.25f) {
                    for(int k = 0; k < 4; k++){
                        biConsumer.accept(blockPos.above(j).relative(Direction.EAST, k), ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource,blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                }
            }

            if(i % 2 == 0 && randomSource.nextBoolean()){
                if(randomSource.nextFloat() > 0.25f) {
                    for(int k = 0; k < 4; k++){
                        biConsumer.accept(blockPos.above(j).relative(Direction.WEST, k), ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource,blockPos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
                    }
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(height), 0, false));
    }
}
