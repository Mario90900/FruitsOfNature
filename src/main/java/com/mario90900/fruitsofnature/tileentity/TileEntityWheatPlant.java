package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;

import net.minecraft.world.World;

public class TileEntityWheatPlant extends TileGroundPlant{
	public TileEntityWheatPlant() {
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.WHEAT_GROWTH);
	}
	
	public float calcYield(){ //Probably better handled on a case by case basis
		return ((float) AveragePlantStats.WHEAT_YIELD * this.yield); //For wheat, it is expected to return a set amount, modified by Yield.
	}
	
	public int calcPotency(){ //Just a quick math that applies the low-high change to the Potency
		return super.calcPotency(AveragePlantStats.WHEAT_POTENCY);
	}
}
