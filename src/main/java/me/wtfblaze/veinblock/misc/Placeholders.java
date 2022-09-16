package me.wtfblaze.veinblock.misc;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.wtfblaze.veinblock.VeinBlock;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Placeholders extends PlaceholderExpansion
{
    private final VeinBlock plugin;
    public Placeholders(VeinBlock plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "veinblock";
    }

    @Override
    public @NotNull String getAuthor() {
        return "WTFBlaze";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params)
    {
        switch (params)
        {
            case "rank":
            {
                if (plugin.resourceStatus.containsKey(player.getUniqueId()))
                {
                    Boolean status = plugin.resourceStatus.get(player.getUniqueId());
                    if (status)
                    {
                        switch (plugin.perms.getPrimaryGroup(player.getPlayer()))
                        {
                            case "Owner":
                                return "丩";
                            case "Sr-Admin":
                                return "个";
                            case "Admin":
                                return "丫";
                            case "Sr-Mod":
                                return "丬";
                            case "Mod":
                                return "中";
                            case "Elite":
                                return "丮";
                            case "Legend":
                                return "丯";
                            case "Hero":
                                return "丰";
                            default:
                                return "";
                        }
                    }
                }

            }
            return PlaceholderAPI.setPlaceholders(player.getPlayer(), "%vault_rankprefix%");

            case "scoreboard":
            {
                if (plugin.resourceStatus.containsKey(player.getUniqueId()))
                {
                    Boolean status = plugin.resourceStatus.get(player.getUniqueId());
                    if (status) { return "ꌣ"; }
                }
            }
            return "&d&lVein&f&lBlock - &bYour new favorite Skyblock Server!";
        }
        return null;
    }
}
