package ru.maxed.smelting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

class changeDrop {


    @SubscribeEvent
    public void handleBlockDrops(BlockEvent.HarvestDropsEvent e){
        if(e.getState().getBlock() == Blocks.IRON_ORE){
        e.getDrops().clear();
        e.getDrops().add(new ItemStack(Items.IRON_INGOT, 1));
        }
        if(e.getState().getBlock() == Blocks.GOLD_ORE){
            e.getDrops().clear();
            e.getDrops().add(new ItemStack(Items.GOLD_INGOT, 1));
        }
    }

    @SubscribeEvent
    public void handleExpDrops(BlockEvent.BreakEvent e){
        if(e.getState().getBlock() == Blocks.IRON_ORE){
            e.setExpToDrop(100);
        }
        if(e.getState().getBlock() == Blocks.GOLD_ORE){
            e.setExpToDrop(200);
        }
    }
}