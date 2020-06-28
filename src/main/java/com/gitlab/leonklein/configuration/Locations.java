package com.gitlab.leonklein.configuration;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Locations {
  private final File file = new File("plugins/AdvGames", "locations.yml");
  private final FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

  public Locations() {}

  public void setLocationWithName(String name, Location location) {
    fileConfiguration.set(name + ".world", location.getWorld().getName());
    fileConfiguration.set(name + ".x", location.getX());
    fileConfiguration.set(name + ".y", location.getY());
    fileConfiguration.set(name + ".z", location.getZ());
    fileConfiguration.set(name + ".yaw", location.getYaw());
    fileConfiguration.set(name + ".pitch", location.getPitch());
    saveConfig();
  }

  public Location getLocationFromName(String name) {
    World world = Bukkit.getWorld(fileConfiguration.getString(name + ".world"));
    double x = fileConfiguration.getDouble(name + ".x");
    double y = fileConfiguration.getDouble(name + ".y");
    double z = fileConfiguration.getDouble(name + ".z");
    float pitch = fileConfiguration.getInt(name + ".pitch");
    float yaw = fileConfiguration.getInt(name + ".yaw");
    Location location = new Location(world, x, y, z);
    location.setPitch(pitch);
    location.setYaw(yaw);
    return location;
  }

  private void saveConfig() {
    try {
      fileConfiguration.save(file);
    } catch (IOException fileSaveFailure) {
      System.err.println("Can´t save locations.yml: " + fileSaveFailure.getMessage());
    }
  }
}
