package com.chaottic.becquerel.common;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.IdentityHashMap;
import java.util.Map;

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

    // Temporary.
    public static final Object2LongMap<Item> BQ = new Object2LongOpenHashMap<>();

    public static final Map<Block, Block> IRRADIATION = new IdentityHashMap<>();

    @Override
    public void onInitialize() {
        BecquerelBlocks.registerAll();
        BecquerelItems.registerAll();
        BecquerelMobEffects.registerAll();

        BQ.put(BecquerelItems.URANIUM_BLOCK, 1);

        IRRADIATION.put(Blocks.GRASS_BLOCK, Blocks.DIRT);
        IRRADIATION.put(Blocks.PODZOL, Blocks.DIRT);
        IRRADIATION.put(Blocks.MYCELIUM, Blocks.DIRT);
        IRRADIATION.put(Blocks.DIRT, Blocks.COARSE_DIRT);
        IRRADIATION.put(Blocks.OAK_LEAVES, Blocks.AIR);
        IRRADIATION.put(Blocks.BIRCH_LEAVES, Blocks.AIR);
        IRRADIATION.put(Blocks.SPRUCE_LEAVES, Blocks.AIR);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, BecquerelOrePlacements.URANIUM_ORE);
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static double calculateBq(double grams, double atomicMass, double decay) {
        return (grams / atomicMass) * AVAGADRO * (Math.log(2.0D) / decay);
    }
}
