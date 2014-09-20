package com.mario90900.fruitsofnature.item;

import com.mario90900.fruitsofnature.block.BlockWallPlant;
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

public class ItemWallSeeds extends ItemStatedBase implements IPlantable{
	
	private Block plantBlock;
	
	public ItemWallSeeds(Block plant){
		super();
		plantBlock = plant;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
		
		if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
			if (mop.sideHit != 0 && mop.sideHit != 1 && world.getBlock(mop.blockX, mop.blockY, mop.blockZ).isNormalCube() == true){
				BlockWallPlant block;
				switch (mop.sideHit){
				case 5: //Actually the WEST side of the block! Do not listen to the sideHit variable. It is dumb.
					PlantHelper.plantSeeds(world, stack, plantBlock, mop.blockX + 1, mop.blockY, mop.blockZ);
					break;
				case 4: //Actually the EAST side of the block! Do not listen to the sideHit variable. It is dumb.
					PlantHelper.plantSeeds(world, stack, plantBlock, mop.blockX - 1, mop.blockY, mop.blockZ);
					break;
				case 2: //Actually the NORTH side of the block! Do not listen to the sideHit variable. It is dumb.
					PlantHelper.plantSeeds(world, stack, plantBlock, mop.blockX, mop.blockY, mop.blockZ - 1);
					break;
				case 3: //Actually the SOUTH side of the block! Do not listen to the sideHit variable. It is dumb.
					PlantHelper.plantSeeds(world, stack, plantBlock, mop.blockX, mop.blockY, mop.blockZ + 1);
					break;
				}
            	--stack.stackSize;
			}
		}
		
		return stack;
    }
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Cave;
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
