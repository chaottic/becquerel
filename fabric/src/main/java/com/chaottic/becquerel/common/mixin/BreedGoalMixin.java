package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.BecquerelMobEffects;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BreedGoal.class)
public final class BreedGoalMixin {
    @Shadow @Final protected Animal animal;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    public void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (animal.hasEffect(BecquerelMobEffects.RADIATION_POISONING)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canContinueToUse", at = @At("RETURN"), cancellable = true)
    public void canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        if (animal.hasEffect(BecquerelMobEffects.RADIATION_POISONING)) {
            cir.setReturnValue(false);
        }
    }
}
