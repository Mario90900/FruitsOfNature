package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.reference.PlantStats;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityWheatPlant extends TileGroundPlant {
	public TileEntityWheatPlant() {
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, PlantStats.WHEAT_GROWTH);
	}
	
	public int calcYield(Random rand){
		return calcYield(rand, PlantStats.WHEAT_YIELD);
	}
}
