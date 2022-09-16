package me.wtfblaze.veinblock.misc;

import me.swanis.mobcoins.MobCoinsAPI;
import net.brcdev.shopgui.provider.economy.EconomyProvider;
import org.bukkit.entity.Player;

public class ShardsProvider extends EconomyProvider
{
    @Override
    public String getName() {
        return "Shards";
    }

    @Override
    public double getBalance(Player player)
    {
        return MobCoinsAPI.getProfileManager().getProfile(player).getMobCoins();
    }

    @Override
    public void deposit(Player player, double amount)
    {
        //long newBalance = plugin.mobCoins.getProfileManager().getProfile(player).getMobCoins() + new Double(amount).longValue();
        long newBalance = Long.sum(MobCoinsAPI.getProfileManager().getProfile(player).getMobCoins(), new Double(amount).longValue());
        MobCoinsAPI.getProfileManager().getProfile(player).setMobCoins(newBalance);
    }

    @Override
    public void withdraw(Player player, double amount)
    {
        long newBalance = MobCoinsAPI.getProfileManager().getProfile(player).getMobCoins() - new Double(amount).longValue();
        MobCoinsAPI.getProfileManager().getProfile(player).setMobCoins(newBalance);
    }
}
