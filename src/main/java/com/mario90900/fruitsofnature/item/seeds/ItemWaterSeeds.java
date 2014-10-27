package com.mario90900.fruitsofnature.item.seeds;

import com.mario90900.fruitsofnature.item.ItemStatedBase;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemWaterSeeds extends ItemStatedBase implements IPlantable{
	
	private Block plantBlock;
	
	public ItemWaterSeeds(Block plant){
		super();
		plantBlock = plant;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
		
		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && world.getBlock(mop.blockX, mop.blockY, mop.blockZ) == Blocks.water && world.getBlock(mop.blockX, mop.blockY + 1, mop.blockZ) == Blocks.air) {
			PlantHelper.plantSeeds(world, stack, plantBlock, mop.blockX, mop.blockY+1, mop.blockZ);
            --stack.stackSize;
		}
		
		return stack;
    }
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Water;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return plantBlock;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
