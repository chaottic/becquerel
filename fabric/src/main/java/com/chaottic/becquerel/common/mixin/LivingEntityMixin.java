package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelComponents;
import com.chaottic.becquerel.common.BecquerelEntityTypeTags;
import com.chaottic.becquerel.common.BecquerelMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public final class LivingEntityMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        var livingEntity = (LivingEntity) (Object) this;

        var level = livingEntity.level;

        if (!level.isClientSide) {
            var chunkComponent = level.getChunkAt(livingEntity.blockPosition()).getComponent(BecquerelComponents.GRAY);
            if (chunkComponent.getGray() > 1.0D) {
                if (!livingEntity.hasEffect(BecquerelMobEffects.RADIATION_POISONING)) {
                    if (livingEntity instanceof Player player && player.isCreative()) {
                        return;
                    }

                    livingEntity.addEffect(new MobEffectInstance(BecquerelMobEffects.RADIATION_POISONING, 800, 0));
                }
            }
        }
    }

    @Inject(method = "canBeAffected", at = @At("RETURN"), cancellable = true)
    public void canBeAffected(MobEffectInstance mobEffectInstance, CallbackInfoReturnable<Boolean> cir) {
        if (mobEffectInstance.getEffect() == BecquerelMobEffects.RADIATION_POISONING && ((LivingEntity) (Object) this).getType().is(BecquerelEntityTypeTags.RADIATION_IMMUNE)) {
            cir.setReturnValue(false);
        }
    }
}
