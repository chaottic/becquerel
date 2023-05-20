package com.chaottic.becquerel.common.block;

import com.chaottic.becquerel.common.BecquerelComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;

public final class FuelRodBlock extends Block {
    public static final IntegerProperty TEMPERATURE = IntegerProperty.create("temperature", 0, 4);

    public FuelRodBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(TEMPERATURE, 0));
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        var temperature = blockState.getValue(TEMPERATURE);

        if (!surroundedByWater(serverLevel, blockPos)) {
            if (temperature == 4) {
                explode(serverLevel, blockPos);
            } else {
                serverLevel.setBlock(blockPos, blockState.setValue(TEMPERATURE, temperature + 1), Block.UPDATE_ALL);
            }
        } else if (temperature > 0) {
            serverLevel.setBlock(blockPos, blockState.setValue(TEMPERATURE, temperature - 1), Block.UPDATE_ALL);
        }
    }

    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion) {
        if (!level.isClientSide) {
            explode(level, blockPos);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TEMPERATURE);
    }

    private static void explode(Level level, BlockPos blockPos) {
        level.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 5.0F, Level.ExplosionInteraction.BLOCK);

        var chunk = level.getChunk(blockPos);
        var component = chunk.getComponent(BecquerelComponents.GRAY);
        component.addGray(16.0D);

        chunk.syncComponent(BecquerelComponents.GRAY);
    }

    private static boolean surroundedByWater(Level level, BlockPos blockPos) {
        for (var direction : Direction.Plane.HORIZONTAL) {
            if (level.getFluidState(blockPos.relative(direction)).is(Fluids.WATER)) {
                return true;
            }
        }
        return false;
    }
}
