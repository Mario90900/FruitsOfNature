package com.mario90900.fruitsofnature.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.reference.VanillaPlantStats;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemPotatoSeeds extends ItemGroundSeeds{
	public ItemPotatoSeeds(){
		super(ModBlocks.potatoPlant);
		this.setUnlocalizedName(UnlocalizedNames.POTATO_SEEDS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//Sets defaults for each plant seed, to be changed afterwards elsewhere
		PlantHelper.setPotency(stack, VanillaPlantStats.POTATO_POTENCY);
		PlantHelper.setYield(stack, VanillaPlantStats.POTATO_YIELD);
		PlantHelper.setGrowth(stack, VanillaPlantStats.POTATO_GROWTH);
	}
}
