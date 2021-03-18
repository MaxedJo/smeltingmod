package ru.maxed.smelting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid= Smelting.MODID, name= Smelting.MODNAME, version = Smelting.VERSION,acceptableRemoteVersions = "*")
public class Smelting {

    public static final String MODID = "smelting";
    public static final String MODNAME = "SmeltingMod";
    public static final String VERSION = "1.2.0";

    @Mod.EventHandler
    public void load(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new oreBreakIvent());
    }

}
