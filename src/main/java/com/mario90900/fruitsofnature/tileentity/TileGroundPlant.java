package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.utility.LogHelper;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class TileGroundPlant extends TilePlant{
	public TileGroundPlant(){
		super();
	}
	
	/*
	 * Determines if the plant should grow this tick or not, and applies various modifiers to the chance (If the soil is watered for ground plants)
	 */
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		float compValueFloat = (float) defGrowth * this.growth;
		int compValue;
		
		if (!world.getBlock(x, y-1, z).isFertile(world, x, y-1, z)) {
			compValue = (int)(compValueFloat / 2.0);
		} else {
			compValue = (int)compValueFloat;
		}
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
		}
	}
}
