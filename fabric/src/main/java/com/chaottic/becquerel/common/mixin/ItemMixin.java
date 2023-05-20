package com.chaottic.becquerel.common.mixin;

import com.chaottic.becquerel.common.Becquerel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public final class ItemMixin {

    @Inject(method = "appendHoverText", at = @At("RETURN"))
    public void appendHoverText(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag, CallbackInfo ci) {
        var item = itemStack.getItem();

        if (Becquerel.BQ.containsKey(item)) {
            var bq = Becquerel.BQ.getLong(item);

            list.add(Component.translatable("bq.becquerel", bq));
        }
    }
}
