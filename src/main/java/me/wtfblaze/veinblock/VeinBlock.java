package me.wtfblaze.veinblock;

import me.wtfblaze.veinblock.cmds.MineWarp;
import me.wtfblaze.veinblock.cmds.Taunts;
import me.wtfblaze.veinblock.events.*;
import me.wtfblaze.veinblock.misc.Placeholders;
import me.wtfblaze.veinblock.objects.MineEntrance;
import me.wtfblaze.veinblock.objects.TauntCooldown;
import me.wtfblaze.veinblock.objects.TauntType;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class VeinBlock extends JavaPlugin
{
    public Economy econ = null;
    public Permission perms = null;
    public Chat chat = null;
    public HashMap<UUID, Boolean> resourceStatus = new HashMap<>();
    public List<Inventory> openedTauntsInventories = new ArrayList<>();
    public Map<UUID, TauntType> waitingForTauntTarget = new HashMap<>();
    public List<TauntCooldown> tauntCooldowns = new ArrayList<>();
    public MineEntrance currentMineEntrance;

    @Override
    public void onEnable()
    {
        // Setup Dependencies
        if (!setupEconomy())
        {
            Error("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        if (!setupPapi())
        {
            Error("Disabled due to no PlaceholderAPI dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Log("Successfully setup PlaceholderAPI Hook!");
        if (!setupShopGUI())
        {
            Error("Disabled due to no ShopGUI+ dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Log("Successfully setup ShopGUI+ Hook!");
        if (!setupMobCoins())
        {
            Error("Disabled due to no SuperMobCoins dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Log("Successfully setup SuperMobCoins Hook!");
        if (!setupWorldEdit())
        {
            Error("Disabled due to no FastAsyncWorldEdit dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Log("Successfully setup FastAsyncWorldEdit Hook!");

        getServer().getPluginManager().registerEvents(new ShopGUIEnabled(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        getServer().getPluginManager().registerEvents(new ResourcePackStatus(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlace(), this);
        getServer().getPluginManager().registerEvents(new BlockClick(this), this);
        getServer().getPluginManager().registerEvents(new WorldLoaded(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClosed(this), this);
        getServer().getPluginManager().registerEvents(new InventoryClicked(this), this);
        getServer().getPluginManager().registerEvents(new InventoryDragged(this), this);
        getServer().getPluginManager().registerEvents(new me.wtfblaze.veinblock.events.Chat(this), this);
        Objects.requireNonNull(getServer().getPluginCommand("Mine")).setExecutor(new MineWarp());
        Objects.requireNonNull(getServer().getPluginCommand("Taunts")).setExecutor(new Taunts());
        new Placeholders(this).register();
    }

    public void Log(String msg)
    {
        getServer().getConsoleSender().sendMessage(String.format("%s[%sVein%sBlock] %s", ChatColor.WHITE, ChatColor.LIGHT_PURPLE, ChatColor.WHITE, msg));
    }

    public void Error(String msg)
    {
        getServer().getConsoleSender().sendMessage(String.format("%s[%sVein%sBlock] %sERROR %s%s", ChatColor.WHITE, ChatColor.LIGHT_PURPLE, ChatColor.WHITE, ChatColor.RED, ChatColor.WHITE, msg));
    }

    private boolean setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        Log("Successfully setup Vault's Economy Hook!");
        return true;
    }

    private void setupChat()
    {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        assert rsp != null;
        chat = rsp.getProvider();
        Log("Successfully setup Vault's Chat Hook!");
    }

    private void setupPermissions()
    {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        assert rsp != null;
        perms = rsp.getProvider();
        Log("Successfully setup Vault's Chat Hook!");
    }

    private boolean setupPapi()
    {
        return getServer().getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    private boolean setupShopGUI()
    {
        return getServer().getPluginManager().getPlugin("ShopGUIPlus") != null;
    }

    private boolean setupMobCoins()
    {
        return getServer().getPluginManager().getPlugin("SuperMobCoins") != null;
    }

    private boolean setupWorldEdit()
    {
        return getServer().getPluginManager().getPlugin("FastAsyncWorldEdit") != null;
    }
}
