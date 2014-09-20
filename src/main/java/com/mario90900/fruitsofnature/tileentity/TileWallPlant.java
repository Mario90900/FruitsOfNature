package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.block.BlockVine;
import com.mario90900.fruitsofnature.block.BlockWallPlant;
import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileWallPlant extends TilePlant{
	protected Block plantBlock;
	
	public TileWallPlant(Block plant){
		super();
		plantBlock = plant;
	}
	
	public Block getPlantBlock(){
		return plantBlock;
	}
	
	public void onBlockTick(World world, int x, int y, int z, Random rand, int defGrowth) {
		int chance = MathHelper.getRandomIntegerInRange(rand, 1, 100);
		float compValueFloat = (float) defGrowth * this.growth;
		int compValue = (int)compValueFloat;
		
		if (chance <= compValue) {
			int meta = world.getBlockMetadata(x, y, z);
			if (meta < 6){
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, meta + 1, 2);
				PlantHelper.wallPlantExpand(world, this, plantBlock, x, y, z, ((BlockWallPlant)world.getBlock(x, y, z)).growDirection());
			}
		}
	}
}
