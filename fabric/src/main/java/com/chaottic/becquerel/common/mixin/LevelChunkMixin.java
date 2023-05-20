package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.Becquerel;
import com.chaottic.becquerel.common.BecquerelChunk;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
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

        var data = section.getStates().data;

        var int2IntMap = new Int2IntOpenHashMap();
        data.storage().getAll(i -> int2IntMap.addTo(i, 1));

        for (var entry : int2IntMap.int2IntEntrySet()) {
            var item = data.palette().valueFor(entry.getIntKey()).getBlock().asItem();

            if (Becquerel.BQ.containsKey(item)) {
                sum += Becquerel.BQ.getLong(item) * entry.getIntValue();
            }
        }

        return sum;
    }

    private boolean hasBecquerel(LevelChunkSection section) {
        return section.getStates().maybeHas(blockState -> Becquerel.BQ.containsKey(blockState.getBlock().asItem()));
    }
}
