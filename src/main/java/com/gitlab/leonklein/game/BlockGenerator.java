package com.gitlab.leonklein.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockGenerator {
  public BlockGenerator() {}

  private static final int BLOCK_CHUNK_RADIUS = 10;
  private BukkitRunnable bukkitRunnable;
  private final Random random = new Random();

  public void generateBlock(
      World world,
      Plugin plugin
  ) {
    this.bukkitRunnable = new BukkitRunnable() {
      @Override
      public void run() {
        for(Chunk chunk : chunkSpawnArea(world)) {
          int randomX = random.nextInt(15);
          int randomZ = random.nextInt(15);
          int surfaceBlockLevel = getSurfaceBlockLevel(randomX, randomZ, chunk);
          Block block = chunk.getBlock(randomX, surfaceBlockLevel, randomZ);
          placeBlock(block, Material.DIAMOND_BLOCK);
        }
      }
    };
    this.bukkitRunnable.runTask(plugin);
  }

  private ArrayList<Chunk> chunkSpawnArea(World world) {
    ArrayList<Chunk> selectGeneratedChunks = new ArrayList<>();
    Location location = world.getSpawnLocation();
    int baseX = location.getChunk().getX();
    int baseZ = location.getChunk().getZ();
    int bottomX = baseX - BLOCK_CHUNK_RADIUS;
    int bottomZ = baseZ - BLOCK_CHUNK_RADIUS;
    int maxRangeX = (baseX + BLOCK_CHUNK_RADIUS);
    int maxRangeZ = (baseZ + BLOCK_CHUNK_RADIUS);
    for(int chunkX = bottomX; chunkX <= maxRangeX; chunkX++) {
      for(int chunkZ = bottomZ; chunkZ <= maxRangeZ; chunkZ++) {
        Chunk chunk = world.getChunkAt(chunkX, chunkZ);
        selectGeneratedChunks.add(chunk);
      }
    }
    return selectGeneratedChunks;
  }

  private int getSurfaceBlockLevel(
      int x,
      int z,
      Chunk chunk
  ) {
    for(int surfaceLevel = 50; surfaceLevel < 255; surfaceLevel++) {
      if(isBlock(chunk, x, surfaceLevel, z)) {
        return surfaceLevel;
      }
    }
    return 0;
  }

  private boolean isBlock(
      Chunk chunk,
      int x,
      int y,
      int z
  ) {
    List<Biome> blackList
        = Arrays.asList(Biome.OCEAN, Biome.DEEP_OCEAN, Biome.RIVER);
    List<Material> blockReplace
        = Arrays.asList(Material.GRASS, Material.AIR);
    Block block = chunk.getBlock(x, y, z);
    boolean isReplaceable = blockReplace.contains(block.getType());
    boolean isReachable = !blackList.contains(block.getBiome());
    return isReachable && isReplaceable && block.getLightFromSky() != 0;
  }

  private void placeBlock(
      Block block,
      Material material
  ) {
    block.setType(material);
  }
}
