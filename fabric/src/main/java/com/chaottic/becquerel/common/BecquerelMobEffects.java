package com.chaottic.becquerel.common;

import com.chaottic.becquerel.common.effect.RadiationPoisoningEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public final class BecquerelMobEffects {
    public static final MobEffect RADIATION_POISONING = new RadiationPoisoningEffect();

    private BecquerelMobEffects() {}

    public static void registerAll() {
        register("radiation_poisoning", RADIATION_POISONING);
    }

    private static void register(String name, MobEffect mobEffect) {
        Registry.register(BuiltInRegistries.MOB_EFFECT, Becquerel.resourceLocation(name), mobEffect);
    }
}
