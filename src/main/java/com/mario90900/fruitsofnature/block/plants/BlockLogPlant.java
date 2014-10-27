package com.mario90900.fruitsofnature.block.plants;

import java.util.Random;

import com.mario90900.fruitsofnature.tileentity.IStatTilePlant;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public abstract class BlockLogPlant extends BlockPlant{
	public BlockLogPlant(){
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
					if (tile != null){
						tile.onBlockTick(world, x, y, z, rand);
					}
				}
			}
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z){ //Checks the south side first, then East, to North and West. Whichever side has a log of any sort first is the side it renders on.
		int woodID = OreDictionary.getOreID("logWood");
		int[] sBlockIDs = getBlockOreIds(world, x, y, z + 1);
		if (isOreIDsEqual(sBlockIDs, woodID)){ //Is the south block a log of any type?
			this.setBlockBounds(0.0f, 0.0f, 0.875f, 1.0f, 1.0f, 1.0f);
			return;
		}
		
		int[] eBlockIDs = getBlockOreIds(world, x + 1, y, z);
		if (isOreIDsEqual(eBlockIDs, woodID)){ //Is the east block...
			this.setBlockBounds(0.875f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
			return;
		}
		
		int[] nBlockIDs = getBlockOreIds(world, x, y, z - 1);
		if (isOreIDsEqual(nBlockIDs, woodID)){ //Is the north block...
			this.setBlockBounds(0.0f, 0.0f, 0.0F, 1.0f, 1.0f, 0.125f);
			return;
		}
		
		this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.125f, 1.0f, 1.0f); //Must be the west
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z){
		int woodID = OreDictionary.getOreID("logWood");
		int[] sBlockIDs = getBlockOreIds(world, x, y, z + 1);
		if (isOreIDsEqual(sBlockIDs, woodID)){ //Is the south block a log of any type?
			return true;
		}
		
		int[] eBlockIDs = getBlockOreIds(world, x + 1, y, z);
		if (isOreIDsEqual(eBlockIDs, woodID)){ //Is the east block...
			return true;
		}
		
		int[] nBlockIDs = getBlockOreIds(world, x, y, z - 1);
		if (isOreIDsEqual(nBlockIDs, woodID)){ //Is the north block...
			return true;
		}
		
		int[] wBlockIDs = getBlockOreIds(world, x - 1, y, z);
		if (isOreIDsEqual(wBlockIDs, woodID)){ //And the west...
			return true;
		}
		
		return false; //Well, there are no logs, it should be removed.
	}
	
	public static int[] getBlockOreIds(IBlockAccess world, int x, int y, int z){
		return OreDictionary.getOreIDs(new ItemStack(Item.getItemFromBlock(world.getBlock(x, y, z))));
	}
	
	public static boolean isOreIDsEqual(int[] blockIDs, int compareID){
		for (int i = 0; i < blockIDs.length; i++){
			if (blockIDs[i] == compareID){
				return true;
			}
		}
		
		return false; //Should only get here if the compare ID never appears in the array.
	}
}
