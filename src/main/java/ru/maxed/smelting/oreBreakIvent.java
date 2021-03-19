package ru.maxed.smelting;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class oreBreakIvent {
    public int quantityDropped(int fortune, Random random) {
        int i=fortune;
        int luck=random.nextInt(100) ;
        if (luck >= 100-Configs.goodLuck)  i++;
         else if (luck <= Configs.badLuck)  i--;
        return (i > 0 ) ? i : 1;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void dropSmelting(BlockEvent.HarvestDropsEvent event) {
        Random rand = new Random();
        if (Configs.debugMode && event.getHarvester() != null) event.getHarvester().sendMessage(new TextComponentString(event.getState().getBlock().getRegistryName().toString()));
        boolean check =   !Arrays.asList(Configs.oreBlackList).contains(event.getState().getBlock().getRegistryName().toString());
        if(check && event.getHarvester() != null && !event.isSilkTouching() && event.getState().getBlock().getUnlocalizedName().toLowerCase().contains("ore")) {
            ItemStack main = event.getHarvester().getHeldItemMainhand();
            if(!main.isEmpty()) {
                    int fortuneLvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, main);
                        NonNullList<ItemStack> newStacks = NonNullList.create();
                    Iterator<ItemStack> iterator = event.getDrops().iterator();
                    while (iterator.hasNext()) {
                        ItemStack stack = iterator.next();
                        if (stack.isEmpty()) {
                            continue;
                        }
                        ItemStack out = FurnaceRecipes.instance().getSmeltingResult(stack);

                        if (out.isEmpty()) {
                            continue;
                        }
                        iterator.remove();
                        out.setCount(quantityDropped( fortuneLvl,rand));
                        newStacks.add(out.copy());
                    }
                    event.getDrops().addAll(newStacks);
                }
            }
    }

    @SubscribeEvent
    public void handleExpDrops(BlockEvent.BreakEvent event){
        boolean canHarvest = ForgeHooks.canHarvestBlock(event.getState().getBlock(), event.getPlayer(), event.getWorld(), event.getPos());
        boolean check =   !Arrays.asList(Configs.oreBlackList).contains(event.getState().getBlock().getRegistryName().toString());
        if(check && canHarvest  && event.getPlayer() != null && !(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, event.getPlayer().getHeldItemMainhand()) > 0) && event.getState().getBlock().getUnlocalizedName().toLowerCase().contains("ore")){
                event.setExpToDrop(Configs.expFromBlock);
        }
    }
}

