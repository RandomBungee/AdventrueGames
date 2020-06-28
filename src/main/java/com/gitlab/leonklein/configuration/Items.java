package com.gitlab.leonklein.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Items {
  public Items() {}

  private File file = new File("plugins/AdvGames", "items.yml");
  private FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

  public void setDefaultItems() {
    if(itemExist()) {
      List<String> items = fileConfiguration.getStringList("AdvGames.Loot");
      items.add("DIAMOND_CHESTPLATE");
    }
  }

  public boolean itemExist() {
    List<String> blocks = fileConfiguration.getStringList("AdvGames.Loot");
    return blocks.isEmpty();
  }

  public List<String> getItemsFromFile() {
    return fileConfiguration.getStringList("AdvGames.Loot");
  }

  private void saveConfig() {
    try {
      fileConfiguration.save(file);
    } catch (IOException fileSaveFailure) {
      System.err.println("Can´t save locations.yml: " + fileSaveFailure.getMessage());
    }
  }
}
