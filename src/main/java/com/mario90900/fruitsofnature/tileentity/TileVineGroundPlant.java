package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.block.BlockWallPlant;
import com.mario90900.fruitsofnature.utility.LogHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileVineGroundPlant extends TilePlant{
	protected Block plantBlock;
	
	public TileVineGroundPlant(Block plant){
		super();
		plantBlock = plant;
	}
	
	public Block getPlantBlock(){
		return plantBlock;
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		int compValue = (int)((float) defGrowth * this.growth);
		
		if (!world.getBlock(x, y-1, z).isFertile(world, x, y-1, z)) {
			compValue = compValue/2;
		}
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == 2) {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
				PlantHelper.vineGroundPlantExpand(world, this, plantBlock, x, y, z);
			} else if (meta >= 8) {
				world.setBlockMetadataWithNotify(x, y, z, 7, 2);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
			}
		}
	}
}
