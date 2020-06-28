package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import com.gitlab.leonklein.game.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {
  private final TetragonPlugin tetragonPlugin;

  public DamageListener(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void checkPlayerCanDamage(EntityDamageByEntityEvent damageByEntityEvent) {
    if(damageByEntityEvent.getDamager() instanceof Player) {
      Player player = (Player) damageByEntityEvent.getDamager();
      if(tetragonPlugin.game.gameState() != GameState.GAME
          && !tetragonPlugin.game.spectators.contains(player)) {
        damageByEntityEvent.setCancelled(true);
      }
      damageByEntityEvent.setCancelled(false);
    }
  }
}
