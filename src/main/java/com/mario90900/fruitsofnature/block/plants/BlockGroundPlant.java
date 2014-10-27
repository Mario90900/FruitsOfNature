package com.mario90900.fruitsofnature.block.plants;

import java.util.Random;

import net.minecraft.world.World;

import com.mario90900.fruitsofnature.tileentity.IStatTilePlant;

public abstract class BlockGroundPlant extends BlockPlant{
	public BlockGroundPlant(){
		super();
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		if (!world.isRemote){
			super.updateTick(world, x, y, z, rand);
			
			if (world.getBlockLightValue(x, y, z) >= 9) {
				int meta = world.getBlockMetadata(x, y, z);
				
				if (meta < 7){
					IStatTilePlant tile = getPlantTile(world, x, y, z);
					tile.onBlockTick(world, x, y, z, rand);
				}
			}
		}
	}
}
