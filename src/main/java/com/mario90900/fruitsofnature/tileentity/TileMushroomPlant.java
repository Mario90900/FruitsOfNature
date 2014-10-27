package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class TileMushroomPlant extends TilePlant{
	public TileMushroomPlant(){
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		float compValueFloat = (float) defGrowth * this.growth;
		int compValue;
		
		if (world.getLightBrightness(x, y, z) <= 4){
			compValue = (int)(compValueFloat*1.5f);
		} else {
			compValue = (int)compValueFloat;
		}
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
		}
	}
}
