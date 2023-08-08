package com.jamesz.smarterwanderers.ModItems;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import net.minecraft.world.storage.MapIdTracker;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

public class BiomeMapCreator {
    public static void BiomeMapCreator(BlockPos targetPos, ItemStack stack, World world){
        ServerWorld serverWorld = (ServerWorld) world;
        MapIdTracker mapIdTracker = serverWorld.getServer().overworld().getDataStorage().computeIfAbsent(MapIdTracker::new, "idcounts");
        int nextMapId = mapIdTracker.getFreeAuxValueForMap();
        stack.getOrCreateTag().putInt("map", nextMapId);
        MapData mapData = FilledMapItem.getOrCreateSavedData(stack, world);
        mapData.setProperties(targetPos.getX(), targetPos.getZ(), 4, true, true, World.OVERWORLD);
        byte x = (byte) ((targetPos.getX() - mapData.x)/4);
        byte z = (byte) ((targetPos.getZ() - mapData.z)/4);
        MapDecoration marker = new MapDecoration(MapDecoration.Type.RED_X, x, z, (byte) 0, null);
        String markerKey = "biome_red_x";
        mapData.decorations.put(markerKey, marker);
        mapData.setDirty();
        CompoundNBT mapNBT = new CompoundNBT();
        CompoundNBT decorationNBT = new CompoundNBT();
        ListNBT decorationList = new ListNBT();
        Map.Entry<String, MapDecoration> entry = mapData.decorations.entrySet().iterator().next();
        decorationNBT.putString("id", entry.getKey());
        decorationNBT.putInt("type", 26);
        decorationNBT.putInt("x", 0);
        decorationNBT.putInt("z", 0);
        decorationNBT.putInt("rot", entry.getValue().getRot());
        decorationList.add(decorationNBT);
        stack.getOrCreateTag().put("Decorations", decorationList);
    }
}
