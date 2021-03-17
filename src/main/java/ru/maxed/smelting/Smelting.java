package ru.maxed.smelting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid= Smelting.MODID, name= Smelting.MODNAME, version = Smelting.VERSION)
public class Smelting {

    public static final String MODID = "smelting";
    public static final String MODNAME = "SmeltingMod";
    public static final String VERSION = "1.2.0";

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new oreBreakIvent());
    }


    @Config(modid = MODID, type = Type.INSTANCE, name = MODID)
    public static class Configs{
        @Comment({
                "How many exp you will get for one block[DEFAULT=3]"
        })
        public static int expFromBlock=3;
        @Comment({
                "Chance(%) of good Luck (fortune drop + 1 )[DEFAULT=10]"
        })
        @RangeInt(min = 0,max = 100)
        public static int goodLuck=10;
        @Comment({
                "Chance(%) of bad Luck (fortune drop - 1 )[DEFAULT=20]"
        })
        @RangeInt(min = 0,max = 100)
        public static int badLuck=20;
    }

}
