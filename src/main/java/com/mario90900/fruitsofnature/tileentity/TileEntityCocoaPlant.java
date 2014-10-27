package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.reference.PlantStats;

import net.minecraft.world.World;

public class TileEntityCocoaPlant extends TileLogPlant{
	public TileEntityCocoaPlant(){
		super();
	}
	
	@Override
	public void onBlockTick(World world, int x, int y, int z, Random rand) {
		super.onBlockTick(world, x, y, z, rand, PlantStats.COCOA_GROWTH);
	}

	@Override
	public int calcYield(Random rand) {
		return calcYieldRange(rand, PlantStats.COCOA_YIELD_MIN, PlantStats.COCOA_YIELD_MAX);
	}

}
