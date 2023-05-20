package com.chaottic.becquerel.common.block;

import com.chaottic.becquerel.common.BecquerelComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public final class FuelRodBlock extends Block {
    public static final IntegerProperty TEMPERATURE = IntegerProperty.create("temperature", 0, 4);

    public FuelRodBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(TEMPERATURE, 0));
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        explode(serverLevel, blockPos);
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
}
