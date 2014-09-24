package com.mario90900.fruitsofnature.utility;

import java.util.ArrayList;

import org.apache.commons.lang3.tuple.Pair;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.item.ItemStatedBase;
import com.mario90900.fruitsofnature.tileentity.TilePlant;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PlantHelper {
	
	public static boolean plantSeeds(World world, ItemStack stack, Block plant, int x, int y, int z) { //This will turn the seed in the stack into a proper plant block that it was given, copying over the stats from the ItemStack to the TileEnt
		boolean placed = world.setBlock(x, y, z, plant);
		if (!placed){
			return false;
		}
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TilePlant)) {
			world.setBlockToAir(x, y, z);
			return false;
		}
		
		TilePlant plantTile = (TilePlant) tile;
		plantTile.setStats(getPotency(stack), getYield(stack), getGrowth(stack));
		
		return true;
	}
	
	public static boolean wallPlantExpand(World world, TileEntity tile, Block plant, int x, int y, int z, boolean dir){ //Called when a wall plant is grown fully, either by time or by bonemeal
		TilePlant sentPlantTile = (TilePlant) tile;
		boolean placed = false;
		TileEntity tempTile = tile; //Temporary to initialize it, gets overwritten later
		
		if (dir){
			if (world.isAirBlock(x, y + 1, z)){
				placed = world.setBlock(x, y + 1, z, plant);
				if (!placed){
					return false;
				}
				tempTile = world.getTileEntity(x, y + 1, z);
				if (!(tempTile instanceof TilePlant)){
					world.setBlockToAir(x, y + 1, z);
					return false;
				}
			}
		} else {
			if (world.isAirBlock(x, y - 1, z)){
				placed = world.setBlock(x, y - 1, z, plant);
				if (!placed){
					return false;
				}
				tempTile = world.getTileEntity(x, y - 1, z);
				if (!(tempTile instanceof TilePlant)){
					world.setBlockToAir(x, y - 1, z);
					return false;
				}
			}
		}
		
		TilePlant newPlantTile = (TilePlant) tempTile;
		newPlantTile.setStats(sentPlantTile.getPotencyInt(), sentPlantTile.getYieldInt(), sentPlantTile.getGrowthInt());
		
		return true;
	}
	
	public static boolean vineGroundPlantExpand(World world, TileEntity tile, Block plant, int x, int y, int z){
		TilePlant sentPlantTile = (TilePlant) tile;
		ArrayList<Integer> possibleBlocks = new ArrayList<Integer>();
		
		for (int i = 0; i < 4; i++){
			switch (i) {
			case 0:
				if (world.isAirBlock(x, y, z + 1) && world.getBlock(x, y - 1, z + 1) == Blocks.farmland){
					possibleBlocks.add(x);
					possibleBlocks.add(z + 1);
				}
				break;
			case 1:
				if (world.isAirBlock(x + 1, y, z) && world.getBlock(x + 1, y - 1, z) == Blocks.farmland){
					possibleBlocks.add(x + 1);
					possibleBlocks.add(z);
				}
				break;
			case 2:
				if (world.isAirBlock(x, y, z - 1) && world.getBlock(x, y - 1, z - 1) == Blocks.farmland){
					possibleBlocks.add(x);
					possibleBlocks.add(z - 1);
				}
				break;
			case 3:
				if (world.isAirBlock(x - 1, y, z) && world.getBlock(x - 1, y - 1, z) == Blocks.farmland){
					possibleBlocks.add(x - 1);
					possibleBlocks.add(z);
				}
				break;
			}
		}
		
		int numOptions = possibleBlocks.size() / 2;
		
		if (numOptions == 0) {
			return false;
		} else {
			int blockInt = MathHelper.getRandomIntegerInRange(world.rand, 0, numOptions - 1);
			boolean placed = world.setBlock(possibleBlocks.get(blockInt), y, possibleBlocks.get(blockInt + 1), plant, 4, 2);
			if (!placed){
				return false;
			}
			
			TileEntity tempTile = world.getTileEntity(x, y, z);
			if (!(tempTile instanceof TilePlant)) {
				world.setBlockToAir(x, y, z);
				return false;
			}
			
			TilePlant plantTile = (TilePlant) tempTile;
			plantTile.setStats(sentPlantTile.getPotencyInt(), sentPlantTile.getYieldInt(), sentPlantTile.getGrowthInt());
		}
		
		return true;
	}
	
	public static void setStats(ItemStack stack, int potency, int yield, int growth){
		setPotency(stack, potency);
		setYield(stack, yield);
		setGrowth(stack, growth);
	}
	
	public static void setPotency(ItemStack stack, int value){
		NBTHelper.setInteger(stack, "Potency", value);
	}
	
	public static void setYield(ItemStack stack, int value){
		NBTHelper.setInteger(stack, "Yield", value);
	}
	
	public static void setGrowth(ItemStack stack, int value){
		NBTHelper.setInteger(stack, "Growth", value);
	}
	
	public static int getPotency(ItemStack stack){
		return NBTHelper.getInt(stack, "Potency");
	}
	
	public static int getYield(ItemStack stack){
		return NBTHelper.getInt(stack, "Yield");
	}
	
	public static int getGrowth(ItemStack stack){
		return NBTHelper.getInt(stack, "Growth");
	}
}
