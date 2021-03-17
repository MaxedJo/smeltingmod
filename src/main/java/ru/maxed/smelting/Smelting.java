package ru.maxed.smelting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = "smelting")
public class Smelting {

    @Mod.EventHandler
    public void load(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new oreBreakIvent());
    }

}
