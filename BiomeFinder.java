package com.jamesz.smarterwanderers.ModItems;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;

public class BiomeFinder {
    public static BlockPos nearestBiome(ResourceLocation targetBiome, BlockPos startingPos, World world) {
        boolean foundTarget = false;
        BlockPos nearestBiome = null;

        int sampleDistance = 50;
        int maxRadius = 100000;
        int currentRadius = 0;
        int currentX = 0;
        int currentZ = 0;

        while (currentRadius <= maxRadius && !foundTarget) {

            if (currentX == currentRadius && currentZ == currentRadius) {
                BlockPos checkingPos = startingPos.offset(currentX, 0, currentZ);
                ResourceLocation currentBiome = world.getBiome(checkingPos).getRegistryName();
                if (currentBiome.equals(targetBiome)) {
                    LogManager.getLogger().info("Nearest Mushroom Island biome at X: " + checkingPos.getX() + ", Z: " + checkingPos.getZ());
                    nearestBiome = checkingPos;
                    foundTarget = true;
                    break;
                }
                currentRadius = currentRadius + sampleDistance;
                currentX = currentX + sampleDistance;
                LogManager.getLogger().info(currentRadius);
            }

            while (currentZ > -currentRadius) {
                currentZ = currentZ - sampleDistance;
                BlockPos checkingPos = startingPos.offset(currentX, 0, currentZ);
                ResourceLocation currentBiome = world.getBiomeManager().getBiome(checkingPos).getRegistryName();
                if (currentBiome.equals(targetBiome)) {
                    LogManager.getLogger().info("Nearest Mushroom Island biome at X: " + checkingPos.getX() + ", Z: " + checkingPos.getZ());
                    nearestBiome = checkingPos;
                    foundTarget = true;
                    break;
                }
            }

            while (currentX > -currentRadius) {
                currentX = currentX - sampleDistance;
                BlockPos checkingPos = startingPos.offset(currentX, 0, currentZ);
                ResourceLocation currentBiome = world.getBiomeManager().getBiome(checkingPos).getRegistryName();
                if (currentBiome.equals(targetBiome)) {
                    LogManager.getLogger().info("Nearest Mushroom Island biome at X: " + checkingPos.getX() + ", Z: " + checkingPos.getZ());
                    nearestBiome = checkingPos;
                    foundTarget = true;
                    break;
                }
            }

            while (currentZ < currentRadius) {
                currentZ = currentZ + sampleDistance;
                BlockPos checkingPos = startingPos.offset(currentX, 0, currentZ);
                ResourceLocation currentBiome = world.getBiomeManager().getBiome(checkingPos).getRegistryName();
                if (currentBiome.equals(targetBiome)) {
                    LogManager.getLogger().info("Nearest Mushroom Island biome at X: " + checkingPos.getX() + ", Z: " + checkingPos.getZ());
                    nearestBiome = checkingPos;
                    foundTarget = true;
                    break;
                }
            }

            while (currentX < currentRadius) {
                currentX = currentX + sampleDistance;
                BlockPos checkingPos = startingPos.offset(currentX, 0, currentZ);
                ResourceLocation currentBiome = world.getBiomeManager().getBiome(checkingPos).getRegistryName();
                if (currentBiome.equals(targetBiome)) {
                    LogManager.getLogger().info("Nearest Mushroom Island biome at X: " + checkingPos.getX() + ", Z: " + checkingPos.getZ());
                    nearestBiome = checkingPos;
                    foundTarget = true;
                    break;
                }
            }
            if (foundTarget) {
                LogManager.getLogger().info("found target");
                break;
            }
        }
        return nearestBiome;
    }
}