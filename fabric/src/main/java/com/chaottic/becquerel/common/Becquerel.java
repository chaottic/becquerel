package com.chaottic.becquerel.common;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public final class Becquerel implements ModInitializer {
    private static final String MOD_ID = "becquerel";

    private static final CreativeModeTab CREATIVE_MODE_TAB = FabricItemGroup.builder(resourceLocation("becquerel"))
            .icon(BecquerelItems.URANIUM_BLOCK::getDefaultInstance)
            .displayItems((parameters, output) -> {
                output.accept(BecquerelItems.URANIUM_ORE);
                output.accept(BecquerelItems.DEEPSLATE_URANIUM_ORE);
                output.accept(BecquerelItems.URANIUM_BLOCK);
                output.accept(BecquerelItems.FUEL_ROD_BLOCK);
                output.accept(BecquerelItems.RAW_URANIUM);
                output.accept(BecquerelItems.URANIUM_INGOT);
                output.accept(BecquerelItems.URANIUM_HEXAFLUORIDE_GAS_BUCKET);
            })
            .build();

    private static final double AVAGADRO = 6.02214179D * Math.pow(10.0D, 23.0D);

    public static final Object2LongMap<Item> BQ = new Object2LongOpenHashMap<>();

    @Override
    public void onInitialize() {
        BecquerelBlocks.registerAll();
        BecquerelItems.registerAll();
        BecquerelMobEffects.registerAll();

        BQ.put(BecquerelItems.URANIUM_ORE, (int) calculateBq(369444.0D, 238.0D, 2221000000000000000.0D));
        BQ.put(BecquerelItems.DEEPSLATE_URANIUM_ORE, (int) calculateBq(369444.0D, 238.0D, 2221000000000000000.0D));
        BQ.put(BecquerelItems.URANIUM_BLOCK, (int) calculateBq(1900000.0D, 235.0D, 22210000000000000.0D));
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static double calculateBq(double grams, double atomicMass, double decay) {
        return (grams / atomicMass) * AVAGADRO * (Math.log(2.0D) / decay);
    }
}
