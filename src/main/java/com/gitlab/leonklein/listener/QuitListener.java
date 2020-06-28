package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import com.gitlab.leonklein.countdown.LobbyCountdown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
  private final TetragonPlugin tetragonPlugin;

  public QuitListener(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void ensureCountdownFurther(PlayerQuitEvent playerQuitEvent) {
    Player player = playerQuitEvent.getPlayer();
    if(Bukkit.getOnlinePlayers().size() >= 2) {
      return;
    }
    LobbyCountdown lobbyCountdown = new LobbyCountdown(60, this.tetragonPlugin);
    lobbyCountdown.cancel();
  }
}
