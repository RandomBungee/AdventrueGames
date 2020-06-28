package com.gitlab.leonklein.configuration;

import com.gitlab.leonklein.TetragonPlugin;
import org.bukkit.entity.Player;

public class Messages {
  private final TetragonPlugin tetragonPlugin;

  public Messages(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  public String messageFromConfig(
      String path,
      Player death,
      Player killer
  ) {
    String message = tetragonPlugin.getConfig().getString(path)
        .replaceAll("%player%", death.getName())
        .replaceAll("%killer%", killer.getName());
    return message;
  }

  public String messageFromConfig(
      String path,
      Player death
  ) {
    String message = tetragonPlugin.getConfig().getString(path)
        .replaceAll("%player%", death.getName());
    return message;
  }

  public String messageFromConfig(
      String path,
      int count
  ) {
    String message = tetragonPlugin.getConfig().getString(path)
        .replaceAll("%count%", String.valueOf(count));
    return message;
  }

  public String messageFromConfig(String path) {
    String message = tetragonPlugin.getConfig().getString(path);
    return message;
  }
}
