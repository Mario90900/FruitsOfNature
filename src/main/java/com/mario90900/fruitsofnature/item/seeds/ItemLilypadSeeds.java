package com.mario90900.fruitsofnature.item.seeds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.reference.VanillaPlantStats;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemLilypadSeeds extends ItemWaterSeeds{
	public ItemLilypadSeeds(){
		super(ModBlocks.lilypadPlant);
		this.setUnlocalizedName(UnlocalizedNames.LILYPAD_SEEDS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//Sets defaults for each plant seed, to be changed afterwards elsewhere
		PlantHelper.setPotency(stack, VanillaPlantStats.LILYPAD_POTENCY);
		PlantHelper.setYield(stack, VanillaPlantStats.LILYPAD_YIELD);
		PlantHelper.setGrowth(stack, VanillaPlantStats.LILYPAD_GROWTH);
	}
}
