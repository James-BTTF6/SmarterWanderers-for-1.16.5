package com.jamesz.smarterwanderers;

import com.jamesz.smarterwanderers.ModItems.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "smarterwanderers");

    //BlockItems
    //Polished_cobble
    public static final RegistryObject<Item> MUSHROOM_MAP = ITEMS.register("mushroom_map", () -> new MushroomExplorerMapItem(new Item.Properties()));
    public static final RegistryObject<BlockItem> POLISHED_COBBLESTONE_ITEM = ITEMS.register("polished_cobblestone", () -> new BlockItem((Block)Blocks.POLISHED_COBBLESTONE.get(), new Item.Properties()));
}
