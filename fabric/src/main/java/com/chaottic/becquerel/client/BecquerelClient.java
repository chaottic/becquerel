package com.chaottic.becquerel.client;

import com.chaottic.becquerel.common.BecquerelBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public final class BecquerelClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BecquerelBlocks.URANIUM_GAS, RenderType.translucent());
    }
}
