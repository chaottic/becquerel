package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.Becquerel;
import com.chaottic.becquerel.common.BecquerelChunk;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Arrays;

@Mixin(LevelChunk.class)
public final class LevelChunkMixin implements BecquerelChunk {
    @Override
    public long getBecquerel() {
        return Arrays.stream(((LevelChunk) (Object) this).getSections()).filter(this::hasBecquerel).mapToLong(this::getBecquerel).sum();
    }

    private long getBecquerel(LevelChunkSection section) {
        var sum = 0;
        for (var i = 0; i < 16; i++) {
            for (var j = 0; j < 16; j++) {
                for (var k = 0; k < 16; k++) {
                    var item = section.getBlockState(i, j, k).getBlock().asItem();

                    sum += Becquerel.BQ.containsKey(item) ? Becquerel.BQ.getLong(item) : 0;
                }
            }
        }

        return sum;
    }

    private boolean hasBecquerel(LevelChunkSection section) {
        return section.getStates().maybeHas(blockState -> Becquerel.BQ.containsKey(blockState.getBlock().asItem()));
    }
}
