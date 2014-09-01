package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.world.World;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;

public class TileEntityLilypadPlant extends TileWaterPlant{
	public TileEntityLilypadPlant(){
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.LILYPAD_GROWTH);
	}
	
	public float calcYield(){ //Probably better handled on a case by case basis
		return ((float) AveragePlantStats.LILYPAD_YIELD * this.yield); //For wheat, it is expected to return a set amount, modified by Yield.
	}
}
