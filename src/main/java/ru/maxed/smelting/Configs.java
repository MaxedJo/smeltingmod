package ru.maxed.smelting;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = Smelting.MODID, type = Type.INSTANCE, name = Smelting.MODID)
    public class Configs{
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
        @Comment({
                "Write ores separated with , ",
                "Example minecraft:gold_ore,minecraft:iron_ore"
        })
        public static String oreBlackList = "minecraft:gold_ore";
}
