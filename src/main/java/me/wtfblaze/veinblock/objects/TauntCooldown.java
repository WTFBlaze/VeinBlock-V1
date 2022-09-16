package me.wtfblaze.veinblock.objects;

import java.util.Calendar;
import java.util.UUID;

public class TauntCooldown
{
    public TauntCooldown(Calendar cal, UUID owner, TauntType type)
    {
        calendar = cal;
        uuid = owner;
        tauntType = type;
    }

    public Calendar calendar;
    public UUID uuid;
    public TauntType tauntType;
}
