package com.chaottic.becquerel.common.mixin;

import net.minecraft.server.level.ServerChunkCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerChunkCache.class)
public final class ServerChunkCacheMixin {

    @Inject(method = "tickChunks", at = @At("RETURN"))
    public void tickChunks(CallbackInfo ci) {
    }
}
