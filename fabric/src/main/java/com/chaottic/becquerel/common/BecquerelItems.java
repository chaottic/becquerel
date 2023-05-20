package com.chaottic.becquerel.common;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class BecquerelItems {
    public static final Item URANIUM_ORE = new BlockItem(BecquerelBlocks.URANIUM_ORE, new Item.Properties());
    public static final Item DEEPSLATE_URANIUM_ORE = new BlockItem(BecquerelBlocks.DEEPSLATE_URANIUM_ORE, new Item.Properties());
    public static final Item URANIUM_BLOCK = new BlockItem(BecquerelBlocks.URANIUM_BLOCK, new Item.Properties());
    public static final Item FUEL_ROD_BLOCK = new BlockItem(BecquerelBlocks.FUEL_ROD_BLOCK, new Item.Properties());
    public static final Item RAW_URANIUM = new Item(new Item.Properties());
    public static final Item URANIUM_INGOT = new Item(new Item.Properties());

    private BecquerelItems() {}

    public static void registerAll() {
        register("uranium_ore", URANIUM_ORE);
        register("deepslate_uranium_ore", DEEPSLATE_URANIUM_ORE);
        register("uranium_block", URANIUM_BLOCK);
        register("fuel_rod_block", FUEL_ROD_BLOCK);
        register("raw_uranium", RAW_URANIUM);
        register("uranium_ingot", URANIUM_INGOT);
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, Becquerel.resourceLocation(name), item);
    }
}
