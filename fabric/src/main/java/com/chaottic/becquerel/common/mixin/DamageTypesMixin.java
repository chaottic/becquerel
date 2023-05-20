package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageTypes.class)
public interface DamageTypesMixin {

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void bootstrap(BootstapContext<DamageType> bootstapContext, CallbackInfo ci) {
        bootstapContext.register(BecquerelDamageTypes.FATAL_RADIATION_POISONING, new DamageType("fatal_radiation_poisoning", 0.0F));
    }
}
