package com.chaottic.becquerel.common;

import com.chaottic.becquerel.common.Becquerel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class BecquerelOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> URANIUM_ORE = of("uranium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> URANIUM_ORE_SMALL = of("uranium_ore_small");

    private BecquerelOreFeatures() {}

    private static ResourceKey<ConfiguredFeature<?, ?>> of(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Becquerel.resourceLocation(name));
    }
}
