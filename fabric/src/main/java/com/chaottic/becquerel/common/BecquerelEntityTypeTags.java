package com.chaottic.becquerel.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class BecquerelEntityTypeTags {
    public static final TagKey<EntityType<?>> RADIATION_IMMUNE = of("radiation_immune");

    private BecquerelEntityTypeTags() {}

    private static TagKey<EntityType<?>> of(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, Becquerel.resourceLocation(name));
    }
}
