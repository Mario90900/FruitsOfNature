package com.mario90900.fruitsofnature.block.plants;

import java.util.Random;

import net.minecraft.world.World;

import com.mario90900.fruitsofnature.tileentity.IStatTilePlant;

public abstract class BlockWaterPlant extends BlockPlant {
	public BlockWaterPlant(){
		super();
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		if (!world.isRemote){
			super.updateTick(world, x, y, z, rand);
			
			if (world.getBlockLightValue(x, y, z) >= 6) {
				int meta = world.getBlockMetadata(x, y, z);
				
				if (meta < 7){
					IStatTilePlant tile = getPlantTile(world, x, y, z);
					if (tile != null){
						tile.onBlockTick(world, x, y, z, rand);
					}
				}
			}
		}
	}
	
	/*public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB boundingBox, List list, Entity entity) { //I believe this is what makes boats break the vanilla lilypads
    	if (entity == null || !(entity instanceof EntityBoat)) {
        	super.addCollisionBoxesToList(world, x, y, z, boundingBox, list, entity);
    	}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
	}*/ //Got rid of the collision as it was a bit funky.
}
