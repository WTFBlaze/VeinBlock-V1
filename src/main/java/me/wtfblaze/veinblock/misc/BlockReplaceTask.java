package me.wtfblaze.veinblock.misc;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockReplaceTask extends BukkitRunnable
{
    private final Block block;
    private final Material originalOre;

    public BlockReplaceTask(Block block, Material originalOre)
    {
        this.block = block;
        this.originalOre = originalOre;
    }

    @Override
    public void run()
    {
        block.setType(originalOre);
        this.cancel();
    }
}
