package com.chaottic.becquerel.common;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public final class BecquerelDamageTypes {
    public static final ResourceKey<DamageType> FATAL_RADIATION_POISONING = ResourceKey.create(Registries.DAMAGE_TYPE, Becquerel.resourceLocation("fatal_radiation_poisoning"));

    private BecquerelDamageTypes() {}
}
