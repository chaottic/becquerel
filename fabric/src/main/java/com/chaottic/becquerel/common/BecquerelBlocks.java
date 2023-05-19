package com.chaottic.becquerel.common;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class BecquerelBlocks {
    public static final Block URANIUM_ORE = new Block(Properties.copy(Blocks.DIAMOND_BLOCK));
    public static final Block DEEPSLATE_URANIUM_ORE = new Block(Properties.copy(Blocks.DIAMOND_BLOCK));
    public static final Block URANIUM_BLOCK = new Block(Properties.copy(Blocks.DIAMOND_BLOCK));

    private BecquerelBlocks() {}

    public static void registerAll() {
        register("uranium_ore", URANIUM_ORE);
        register("deepslate_uranium_ore", DEEPSLATE_URANIUM_ORE);
        register("uranium_block", URANIUM_BLOCK);
    }

    private static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, Becquerel.resourceLocation(name), block);
    }
}
