package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileWallPlant extends TilePlant{
	public TileWallPlant(){ //TODO properly allow for the growth in whatever direction, and decide on if it should be rightclick harvest or break harvest
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		float compValueFloat = (float) defGrowth * this.growth;
		int compValue = (int)compValueFloat;
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
		}
	}
}
