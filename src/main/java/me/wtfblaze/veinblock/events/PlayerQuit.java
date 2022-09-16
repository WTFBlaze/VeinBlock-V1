package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener
{
    private final VeinBlock plugin;
    public PlayerQuit(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        plugin.resourceStatus.remove(e.getPlayer().getUniqueId());
    }
}
