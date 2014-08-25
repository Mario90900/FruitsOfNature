package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.utility.LogHelper;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileGroundPlant extends TilePlant{
	public TileGroundPlant(){
		super();
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		int compValue = (int)((float) defGrowth * this.growth);
		
		if (!world.getBlock(x, y-1, z).isFertile(world, x, y-1, z)) {
			compValue = compValue/2;
		}
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
		}
	}
	
	/*public int calcYield(int defYield){
		//Keep this here incase something later could help with a default Yield calc?
	}*/
	
	public int calcPotency(int defPotency){
		return (int)((float) defPotency * this.potency);
	}
}