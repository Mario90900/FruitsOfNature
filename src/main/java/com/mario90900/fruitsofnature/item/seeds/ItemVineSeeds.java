package com.mario90900.fruitsofnature.item.seeds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.reference.VanillaPlantStats;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemVineSeeds extends ItemWallSeeds{
	public ItemVineSeeds(){
		super(ModBlocks.vinePlant);
		this.setUnlocalizedName(UnlocalizedNames.VINE_SEEDS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//Sets defaults for each plant seed, to be changed afterwards elsewhere
		PlantHelper.setPotency(stack, VanillaPlantStats.VINE_POTENCY);
		PlantHelper.setYield(stack, VanillaPlantStats.VINE_YIELD);
		PlantHelper.setGrowth(stack, VanillaPlantStats.VINE_GROWTH);
	}
}
