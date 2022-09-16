package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDragged implements Listener
{
    private final VeinBlock plugin;
    public InventoryDragged(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onInventoryDragged(InventoryDragEvent e)
    {
        if (plugin.openedTauntsInventories.contains(e.getInventory()))
        {
            e.setCancelled(true);
        }
    }
}
