package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelComponents;
import com.chaottic.becquerel.common.BecquerelEntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public final class LivingEntityMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        var livingEntity = (LivingEntity) (Object) this;

        var level = livingEntity.level;

        if (!level.isClientSide) {
            var chunkComponent = level.getChunkAt(livingEntity.blockPosition()).getComponent(BecquerelComponents.GRAY);
            if (chunkComponent.getGray() > 0.0D) {

                if (!livingEntity.hasEffect(MobEffects.POISON) && !livingEntity.getType().is(BecquerelEntityTypeTags.RADIATION_IMMUNE)) {
                    if (livingEntity instanceof Player player && player.isCreative()) {
                        return;
                    }

                    livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                }
            }
        }
    }
}
