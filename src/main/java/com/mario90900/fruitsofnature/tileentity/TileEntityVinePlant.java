package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.AveragePlantStats;

public class TileEntityVinePlant extends TileWallPlant{
	public TileEntityVinePlant(){
		super(ModBlocks.vinePlant);
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand){ //This will calculate when the plant should grow, and change the meta accordingly
		super.onBlockTick(world, x, y, z, rand, AveragePlantStats.VINE_GROWTH);
	}
	
	public int calcYield(Random rand){
		int yieldInt = getYieldInt();
		
		switch (yieldInt){
		case 0:
			int temp = MathHelper.getRandomIntegerInRange(rand, -2, 1);
			if (temp < 0) {
				return 0;
			} else {
				return temp;
			}
		case 1:
			return MathHelper.getRandomIntegerInRange(rand, 0, 1);
		case 3:
			return MathHelper.getRandomIntegerInRange(rand, 1, 2);
		default:
			return Math.round((float)AveragePlantStats.VINE_YIELD * getYield());
		}
	}
}
