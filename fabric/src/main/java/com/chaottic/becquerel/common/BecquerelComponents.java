package com.chaottic.becquerel.common;

import com.chaottic.becquerel.common.component.ChunkGrayComponent;
import com.chaottic.becquerel.common.component.EntityGrayComponent;
import com.chaottic.becquerel.common.component.GrayComponent;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import net.minecraft.world.entity.LivingEntity;

public final class BecquerelComponents implements ChunkComponentInitializer, EntityComponentInitializer {
    public static final ComponentKey<GrayComponent> GRAY = ComponentRegistryV3.INSTANCE.getOrCreate(Becquerel.resourceLocation("gray"), GrayComponent.class);

    @Override
    public void registerChunkComponentFactories(ChunkComponentFactoryRegistry registry) {
        registry.register(GRAY, chunkAccess -> new ChunkGrayComponent());
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, GRAY, livingEntity -> new EntityGrayComponent());
    }
}
