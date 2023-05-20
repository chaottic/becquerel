package com.chaottic.becquerel.common.effect;

import com.chaottic.becquerel.common.BecquerelDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public final class RadiationPoisoningEffect extends MobEffect {
    public RadiationPoisoningEffect() {
        super(MobEffectCategory.HARMFUL, 0xFFFFFF);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        var k = 125 >> j;
        if (k > 0) {
            return i % k == 0;
        }
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
        super.applyEffectTick(livingEntity, i);
        livingEntity.hurt(livingEntity.damageSources().source(BecquerelDamageTypes.FATAL_RADIATION_POISONING), 1.0F);
    }
}
