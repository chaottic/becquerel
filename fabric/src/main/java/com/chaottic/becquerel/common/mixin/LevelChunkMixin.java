package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.Becquerel;
import com.chaottic.becquerel.common.BecquerelChunk;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.stream.StreamSupport;

@Mixin(LevelChunk.class)
public abstract class LevelChunkMixin implements BecquerelChunk {
    @Shadow public abstract BlockState getBlockState(BlockPos blockPos);

    @Override
    public long getBecquerel() {
        var chunkPos = ((LevelChunk) (Object) this).getPos();

        return StreamSupport.stream(BlockPos.betweenClosed(
                chunkPos.getMinBlockX(), ((LevelChunk) (Object) this).getMinBuildHeight(), chunkPos.getMinBlockX(),
                chunkPos.getMaxBlockX(), ((LevelChunk) (Object) this).getMaxBuildHeight(), chunkPos.getMaxBlockZ()).spliterator(), false).mapToLong(this::getBecquerel).sum();
    }

    private long getBecquerel(BlockPos blockPos) {
        var item = getBlockState(blockPos).getBlock().asItem();

        return Becquerel.BQ.containsKey(item) ? Becquerel.BQ.getLong(item) : 0;
    }
}
