package com.gitlab.leonklein.game;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class ResetWorld {
  private ResetWorld() {}

  private File file = new File("world");

  public void resetWorld() {
    Bukkit.unloadWorld("world", false);
    World world = Bukkit.getWorld("world");
    System.gc();
    for(File files : file.listFiles()) {
      files.delete();
    }
  }

  public void generateNewWorld() {
    WorldCreator worldCreator = new WorldCreator("world")
        .environment(Environment.NORMAL)
        .generateStructures(true)
        .type(WorldType.LARGE_BIOMES);
    Bukkit.createWorld(worldCreator);
  }
}
