package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.world.World;

import com.mario90900.fruitsofnature.reference.PlantStats;

public class TileEntityBrownMushPlant extends TileMushroomPlant {
	public TileEntityBrownMushPlant(){
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, PlantStats.BROWN_MUSH_GROWTH);
	}
	
	public int calcYield(Random rand){
		return calcYieldRange(rand, PlantStats.BROWN_MUSH_YIELD_MIN, PlantStats.BROWN_MUSH_YIELD_MAX);
	}
}
