package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelChunk;
import com.chaottic.becquerel.common.BecquerelComponents;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.ChunkStatus;
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
            var component = levelChunk2.getComponent(BecquerelComponents.GRAY);

            for (var direction : Direction.Plane.HORIZONTAL) {
                var normal = direction.getNormal();
                var x = chunkPos.x + normal.getX();
                var z = chunkPos.z + normal.getZ();

                var chunkAccess = levelChunk2.getLevel().getChunk(x, z, ChunkStatus.FULL, false);
                if (chunkAccess == null) {
                    continue;
                }

                var chunkAccessComponent = chunkAccess.getComponent(BecquerelComponents.GRAY);

                var gray = component.getGray();
                if (gray >= 2.0D && gray >= chunkAccessComponent.getGray()) {
                    var amount = 1.0D;
                    var remaining = Math.max(gray - amount, 0.0D);

                    component.setGray(remaining);
                    chunkAccessComponent.addGray(amount);

                    levelChunk2.syncComponent(BecquerelComponents.GRAY);
                    chunkAccess.syncComponent(BecquerelComponents.GRAY);
                }
            }

            var bq = ((BecquerelChunk) levelChunk2).getBecquerel();
            if (bq > 0) {
                levelChunk2.getComponent(BecquerelComponents.GRAY).addGray(bq / 20.0D);
                levelChunk2.syncComponent(BecquerelComponents.GRAY);
            }
        }
    }
}
