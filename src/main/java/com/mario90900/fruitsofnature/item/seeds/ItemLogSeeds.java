package com.mario90900.fruitsofnature.item.seeds;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

import com.mario90900.fruitsofnature.item.ItemStatedBase;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class ItemLogSeeds extends ItemStatedBase implements IPlantable{
	
	private Block plantBlock;
	
	public ItemLogSeeds(Block plant){
		super();
		plantBlock = plant;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float facingX, float facingY, float facingZ) {
		if (side == 1 || side == 0) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
        	int woodID = OreDictionary.getOreID("logWood");
        	int[] blockIDs = OreDictionary.getOreIDs(new ItemStack(world.getBlock(x, y, z).getItem(world, x, y, z)));
        	for (int i = 0; i < blockIDs.length; i++){
        		if (blockIDs[i] == woodID){
        			switch (side){
        				case 2:
        					--z;
        					break;
        				case 3:
        					++z;
        					break;
        				case 4:
        					--x;
        					break;
        				case 5:
        					++x;
        					break;
	        			default:
	        				return false;
        			}
        			
        			if (world.isAirBlock(x, y, z)){
        				PlantHelper.plantSeeds(world, stack, plantBlock, x, y, z);
        				--stack.stackSize;
        				return true;
        			} else {
        				return false;
        			}
        		}
        	}
        }
		return false;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}
}
