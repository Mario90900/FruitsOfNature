package com.mario90900.fruitsofnature.block;

import com.mario90900.fruitsofnature.tileentity.TileWallPlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallPlant extends BlockPlant{
	protected boolean direction;
	
	public BlockWallPlant(boolean dir){
		super();
		direction = dir;
	}
	
	public boolean growDirection(){ //Intended for use on Wall Plants to determine if they grow upwards (true), or downwards (false)
		return direction;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){ //Keep this method in all Wall plants
		if (world.getBlock(x, y, z + 1).isNormalCube()){ //Checks the South block
			this.setBlockBounds(0.0f, 0.0f, 0.875f, 1.0f, 1.0f, 1.0f);
		} else if (world.getBlock(x - 1, y, z).isNormalCube()){ //Checks the West block
			this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.125f, 1.0f, 1.0f);
		} else if (world.getBlock(x, y, z - 1).isNormalCube()){ //Checks the North block
			this.setBlockBounds(0.0f, 0.0f, 0.0F, 1.0f, 1.0f, 0.125f);
		} else { //Defaults to the East side - This method only gets called if it can stay, so it must have a block somewhere
			this.setBlockBounds(0.875f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
		}
	}
	
	@Override
	public void boneMealGrow(World world, int x, int y, int z) { //The method that actually "grows" the plant after a piece of bonemeal is used.
        int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

        if (l >= 7) {
            world.setBlockMetadataWithNotify(x, y, z, 7, 2);
            PlantHelper.wallPlantExpand(world, world.getTileEntity(x, y, z), ((TileWallPlant)world.getTileEntity(x, y, z)).getPlantBlock(), x, y, z, growDirection());
        } else {
        	world.setBlockMetadataWithNotify(x, y, z, l, 2);
        }
    }
	
	@Override
    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity){
        if (world.getBlockMetadata(x, y, z) >= 4){
        	return true;
        } else {
        	return false;
        }
    }
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		if (world.getBlock(x, y, z + 1).isNormalCube()){
			return true;
		} else if (world.getBlock(x - 1, y, z).isNormalCube()){
			return true;
		} else if (world.getBlock(x, y, z - 1).isNormalCube()){
			return true;
		} else if (world.getBlock(x + 1, y, z).isNormalCube()){
			return true;
		} else {
			return false;
		}
	}
}
