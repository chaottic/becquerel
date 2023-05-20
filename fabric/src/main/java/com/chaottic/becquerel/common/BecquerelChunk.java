package com.chaottic.becquerel.common;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.world.level.chunk.LevelChunkSection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface BecquerelChunk {

    long getBecquerel();

    static List<LevelChunkSection> filterForIrradiation(List<ServerChunkCache.ChunkAndHolder> list) {
        var sections = new ArrayList<LevelChunkSection>();
        for (var chunkAndHolder : list) {
            var levelChunk = chunkAndHolder.chunk();

            var component = levelChunk.getComponent(BecquerelComponents.GRAY);
            if (component.getGray() > 0.0D) {
                for (var section : levelChunk.getSections()) {
                    if (section.getStates().maybeHas(blockState -> Becquerel.IRRADIATION.containsKey(blockState.getBlock()))) {
                        sections.add(section);
                    }
                }
            }
        }

        return Collections.unmodifiableList(sections);
    }
}
