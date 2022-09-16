package me.wtfblaze.veinblock.events;

import me.wtfblaze.veinblock.VeinBlock;
import me.wtfblaze.veinblock.misc.ShardsProvider;
import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopGUIPlusPostEnableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopGUIEnabled implements Listener
{
    private final VeinBlock plugin;
    public ShopGUIEnabled(VeinBlock plugin) { this.plugin = plugin; }

    @EventHandler
    public void onSGPPostEnable(ShopGUIPlusPostEnableEvent e)
    {
        ShopGuiPlusApi.registerEconomyProvider(new ShardsProvider());
        plugin.Log("Registered Shards economy provider in ShopGUI+");
    }
}
