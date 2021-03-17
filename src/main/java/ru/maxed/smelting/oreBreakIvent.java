package ru.maxed.smelting;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Random;


public class oreBreakIvent {

    public int quantityDropped(int fortune, Random random) {
        if (fortune > 0 )
        {
            int i = random.nextInt(fortune + 2)-1;

            if (i < 0)
            {
                i = 0;
            }

            return  (i + 1);
        }
        else
        {
            return 1;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void dropSmelting(BlockEvent.HarvestDropsEvent event) {
        Random rand = new Random();
        if(event.getHarvester() != null && !event.isSilkTouching() && event.getState().getBlock().getUnlocalizedName().toLowerCase().contains("ore")) {
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
                        newStacks.add(new ItemStack(out.getItem(), quantityDropped(fortuneLvl,rand)));
                    }
                    event.getDrops().addAll(newStacks);
                }
            }
    }

    @SubscribeEvent
    public void handleExpDrops(BlockEvent.BreakEvent event){
        boolean canHarvest = ForgeHooks.canHarvestBlock(event.getState().getBlock(), event.getPlayer(), event.getWorld(), event.getPos());
        if(canHarvest  && event.getPlayer() != null && !(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, event.getPlayer().getHeldItemMainhand()) > 0) && event.getState().getBlock().getUnlocalizedName().toLowerCase().contains("ore")){
            event.setExpToDrop(10);
        }
    }
}

