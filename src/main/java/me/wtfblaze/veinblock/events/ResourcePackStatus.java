package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class ResourcePackStatus implements Listener
{
    private final VeinBlock plugin;
    public ResourcePackStatus(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onResourcePack(PlayerResourcePackStatusEvent e)
    {
        Player player = e.getPlayer();
        if (e.getStatus() == PlayerResourcePackStatusEvent.Status.ACCEPTED || e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)
        {
            plugin.resourceStatus.put(player.getUniqueId(), true);
        }
        else
        {
            plugin.resourceStatus.put(player.getUniqueId(), false);
        }
    }
}
