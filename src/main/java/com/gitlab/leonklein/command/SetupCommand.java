package com.gitlab.leonklein.command;

import com.gitlab.leonklein.TetragonPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {
  private final TetragonPlugin tetragonPlugin;

  public SetupCommand(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @Override
  public boolean onCommand(
      CommandSender sender,
      Command command,
      String label,
      String[] arguments) {
    if(!checkCommandComponents(sender, command)) {
      Player player = (Player)sender;
      tetragonPlugin.locations.setLocationWithName("spawn", player.getLocation());
      player.sendMessage("§aDu hast die Location gesetzt!");
    }
    return false;
  }

  private boolean checkCommandComponents(
      CommandSender commandSender,
      Command command
  ) {
    if(!(commandSender instanceof Player)) {
      return true;
    }
    if(!commandSender.hasPermission("royalpixels.setup")) {
      return true;
    }
    if(!command.getName().equalsIgnoreCase("setup")) {
      return true;
    }
    return false;
  }
}
