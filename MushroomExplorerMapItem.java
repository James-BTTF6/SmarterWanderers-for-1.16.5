package com.jamesz.smarterwanderers.ModItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

public class MushroomExplorerMapItem extends FilledMapItem {
    public MushroomExplorerMapItem(Properties settings) {
        super(settings);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {

        if (!world.isClientSide) {
            LogManager.getLogger().info("Clicked");

            BlockPos playerPos = player.blockPosition();
            ResourceLocation targetBiome = ForgeRegistries.BIOMES.getValue(Biomes.MUSHROOM_FIELDS.location()).getRegistryName();
            BlockPos nearestBiome = BiomeFinder.nearestBiome(targetBiome, playerPos, world);
            
            if (nearestBiome != null){
                if (world instanceof ServerWorld) {
                    ExperimentalMapCreator.ExperimentalMapCreator(world, player, player.getUsedItemHand(), nearestBiome);
                }
            }
        }
    }
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        LogManager.getLogger().info("NBT tag:" + context.getItemInHand().getTag().toString());
        MapData mapData = FilledMapItem.getOrCreateSavedData(context.getItemInHand(), context.getLevel());
        mapData.load(context.getItemInHand().getTag());
        mapData.setDirty();
        return ActionResultType.SUCCESS;
    }
}
