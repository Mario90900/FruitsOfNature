package com.mario90900.fruitsofnature.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.VanillaPlantStats;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemCarrotSeeds extends ItemGroundSeeds {
	public ItemCarrotSeeds(){
		super(ModBlocks.carrotPlant);
		this.setUnlocalizedName(UnlocalizedNames.CARROT_SEEDS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//Sets defaults for each plant seed, to be changed afterwards elsewhere
		PlantHelper.setPotency(stack, VanillaPlantStats.CARROT_POTENCY);
		PlantHelper.setYield(stack, VanillaPlantStats.CARROT_YIELD);
		PlantHelper.setGrowth(stack, VanillaPlantStats.CARROT_GROWTH);
	}
}
