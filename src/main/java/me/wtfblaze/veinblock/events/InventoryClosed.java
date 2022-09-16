package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClosed implements Listener
{
    private final VeinBlock plugin;
    public InventoryClosed(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onInventoryClosed(InventoryCloseEvent e)
    {
        plugin.openedTauntsInventories.remove(e.getInventory());
    }
}
