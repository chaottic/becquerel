package com.chaottic.becquerel.common;

import com.chaottic.becquerel.common.Becquerel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class BecquerelOrePlacements {
    public static final ResourceKey<PlacedFeature> URANIUM_ORE = of("uranium_ore");

    private BecquerelOrePlacements() {}

    private static ResourceKey<PlacedFeature> of(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Becquerel.resourceLocation(name));
    }
}
