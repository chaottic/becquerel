package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelChunk;
import com.chaottic.becquerel.common.BecquerelComponents;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.LevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

@Mixin(ServerChunkCache.class)
public final class ServerChunkCacheMixin {

    @Inject(method = "tickChunks", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/chunk/LevelChunk;incrementInhabitedTime(J)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    public void tickChunks(CallbackInfo ci, long l, long m, boolean bl, LevelData levelData, ProfilerFiller profilerFiller, int i, boolean bl2, int j, NaturalSpawner.SpawnState spawnState, List list, boolean bl3, Iterator var14, ServerChunkCache.ChunkAndHolder chunkAndHolder, LevelChunk levelChunk2, ChunkPos chunkPos) {
        if (l % 20 == 0) {
            var bq = ((BecquerelChunk) levelChunk2).getBecquerel();
            if (bq > 0) {
                levelChunk2.getComponent(BecquerelComponents.GRAY).addGray(bq / 20.0D);
                levelChunk2.syncComponent(BecquerelComponents.GRAY);
            }
        }
    }
}
