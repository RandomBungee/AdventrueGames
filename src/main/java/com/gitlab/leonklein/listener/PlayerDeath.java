package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerDeath implements Listener {
  private final TetragonPlugin tetragonPlugin;

  public PlayerDeath(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void ensurePlayerIsDeath(PlayerDeathEvent playerDeathEvent) {
    Player player = playerDeathEvent.getEntity();
    if(player.getKiller() != null) {
      Player killer = player.getKiller();
      Bukkit.broadcastMessage(tetragonPlugin.messages.messageFromConfig("killMessage", player, killer));
    } else {
      Bukkit.broadcastMessage(tetragonPlugin.messages.messageFromConfig("deathMessage", player));
    }
    player.teleport(tetragonPlugin.locations.getLocationFromName("spawn"));
    tetragonPlugin.game.players.remove(player);
    tetragonPlugin.game.spectators.add(player);
    tetragonPlugin.game.checkForWinner();
    setCompassToPlayer(player);
  }

  private void setCompassToPlayer(Player player) {
    ItemStack itemStack = new ItemStack(Material.COMPASS);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName("§8§l〣 §c§lKompass");
    itemStack.setItemMeta(itemMeta);
    player.getInventory().setItem(0, itemStack);
  }
}
