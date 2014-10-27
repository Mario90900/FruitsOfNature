package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class TileWaterPlant extends TilePlant{
	public TileWaterPlant(){
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		float compValueFloat = (float) defGrowth * this.growth;
		int compValue;
		
		if (world.getBiomeGenForCoords(x, z).isHighHumidity()){
			compValue = (int)(compValueFloat * 1.5);
		} else {
			compValue = (int)compValueFloat;
		}
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
		}
	}
}
