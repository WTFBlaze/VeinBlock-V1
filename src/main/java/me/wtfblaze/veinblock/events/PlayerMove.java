package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener
{
    private final VeinBlock plugin;
    public PlayerMove(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player player = e.getPlayer();
        if (player.getWorld().getName().equalsIgnoreCase("PublicMine"))
        {
            if (plugin.currentMineEntrance != null)
            {
                //plugin.getLogger().info(player.getName() + " - X:" + e.getTo().getX() + " Y:" + e.getTo().getY() + " Z:" + e.getTo().getZ());
                for (Location location : plugin.currentMineEntrance.triggerLocations)
                {
                    if (e.getTo() == null) return;
                    if ((int)e.getTo().getX() == (int)location.getX() &&
                            (int)e.getTo().getY() == (int)location.getY() &&
                            (int)e.getTo().getZ() == (int)location.getZ())
                    {
                        if (!plugin.currentMineEntrance.sentDoorMsg)
                        {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMINES &8&l» &7The chamber doors collapse behind you!"));
                            plugin.currentMineEntrance.sentDoorMsg = true;
                        }
                        for (Location doorLoc : plugin.currentMineEntrance.doorLocations)
                        {
                            doorLoc.getBlock().setType(Material.STONE);
                        }
                        plugin.currentMineEntrance.chestLocation.getBlock().setType(Material.CHEST);
                    }
                }

            }
        }
    }
}
