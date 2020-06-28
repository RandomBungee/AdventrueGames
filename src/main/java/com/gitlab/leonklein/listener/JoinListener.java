package com.gitlab.leonklein.listener;

import com.gitlab.leonklein.TetragonPlugin;
import com.gitlab.leonklein.countdown.LobbyCountdown;
import com.gitlab.leonklein.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
  private final TetragonPlugin tetragonPlugin;

  public JoinListener(TetragonPlugin tetragonPlugin) {
    this.tetragonPlugin = tetragonPlugin;
  }

  @EventHandler
  public void checkToStartGame(PlayerJoinEvent playerJoinEvent) {
    Player player = playerJoinEvent.getPlayer();
    if(tetragonPlugin.game.gameState() != GameState.LOBBY) {
      tetragonPlugin.game.spectators.add(player);
    } else {
      player.teleport(tetragonPlugin.locations.getLocationFromName("spawn"));
      tetragonPlugin.game.players.add(player);
      if(Bukkit.getOnlinePlayers().size() < 2) {
        return;
      }
      LobbyCountdown lobbyCountdown = new LobbyCountdown(60, this.tetragonPlugin);
      lobbyCountdown.startCountDown(tetragonPlugin);
    }
  }
}
