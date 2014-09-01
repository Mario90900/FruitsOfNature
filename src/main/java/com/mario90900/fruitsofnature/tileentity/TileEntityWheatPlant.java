package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityWheatPlant extends TileGroundPlant{
	public TileEntityWheatPlant() {
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.WHEAT_GROWTH);
	}
	
	public int calcYield(Random rand){
		if (yieldInt == 0) {
			return MathHelper.getRandomIntegerInRange(rand, 0, 1);
		} else {
			return (int)(AveragePlantStats.WHEAT_YIELD * this.yield);
		}
	}
}
