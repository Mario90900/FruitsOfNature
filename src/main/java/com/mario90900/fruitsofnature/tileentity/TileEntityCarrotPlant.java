package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;

public class TileEntityCarrotPlant extends TileGroundPlant{
	public TileEntityCarrotPlant() {
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.CARROT_GROWTH);
	}
	
	public int calcYield(Random rand){
		return MathHelper.getRandomIntegerInRange(rand, calcYieldMin(), calcYieldMax());
	}
	
	public int calcYieldMin(){
		return Math.round((float) AveragePlantStats.CARROT_YIELD_MIN * this.yield);
	}
	
	public int calcYieldMax(){
		return Math.round((float) AveragePlantStats.CARROT_YIELD_MAX * this.yield);
	}
}
