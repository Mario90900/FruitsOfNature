package com.mario90900.fruitsofnature.item.seeds;

import com.mario90900.fruitsofnature.item.ItemStatedBase;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemGroundSeeds extends ItemStatedBase implements IPlantable{
	
	private Block plantBlock;
	
	public ItemGroundSeeds(Block plant){
		super();
		plantBlock = plant;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float facingX, float facingY, float facingZ) {
        if (side != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
            if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)){
                PlantHelper.plantSeeds(world, stack, plantBlock, x, y+1, z);
                --stack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
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
