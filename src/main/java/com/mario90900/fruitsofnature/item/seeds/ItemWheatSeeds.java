package com.mario90900.fruitsofnature.item.seeds;

import com.mario90900.fruitsofnature.block.plants.BlockWheat;
import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.VanillaPlantStats;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.utility.LogHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class ItemWheatSeeds extends ItemGroundSeeds{
	public ItemWheatSeeds(){
		super(ModBlocks.wheatPlant);
		this.setUnlocalizedName(UnlocalizedNames.WHEAT_SEEDS);
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		//Sets defaults for each plant seed, to be changed afterwards elsewhere
		PlantHelper.setPotency(stack, VanillaPlantStats.WHEAT_POTENCY);
		PlantHelper.setYield(stack, VanillaPlantStats.WHEAT_YIELD);
		PlantHelper.setGrowth(stack, VanillaPlantStats.WHEAT_GROWTH);
	}
}