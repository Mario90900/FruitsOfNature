package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.world.World;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;

public class TileEntityPotatoPlant extends TileGroundPlant{
	public TileEntityPotatoPlant() {
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.POTATO_GROWTH);
	}
	
	public float calcYield(){ //Probably better handled on a case by case basis
		return ((float) AveragePlantStats.POTATO_YIELD_MIN * this.yield); //For wheat, it is expected to return a set amount, modified by Yield.
	}
	
	public float calcYieldMax(){
		return ((float) AveragePlantStats.POTATO_YIELD_MAX * this.yield);
	}
	
	public int calcPotency(){ //Just a quick math that applies the low-high change to the Potency
		return super.calcPotency(AveragePlantStats.POTATO_POTENCY);
	}
}
