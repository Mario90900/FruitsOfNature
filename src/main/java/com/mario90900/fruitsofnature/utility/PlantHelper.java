package com.mario90900.fruitsofnature.utility;

import com.mario90900.fruitsofnature.item.ItemStatedBase;
import com.mario90900.fruitsofnature.tileentity.TilePlant;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PlantHelper {
	
	public static boolean plantSeeds(World world, ItemStack stack, Block plant, int x, int y, int z) { //This will turn the seed in the stack into a proper plant block that it was given, copying over the stats from the ItemStack to the TileEnt
		boolean placed = world.setBlock(x, y, z, plant);
		if (!placed)
			return false;
		
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TilePlant)) {
			world.setBlockToAir(x, y, z);
			return false;
		}
		
		TilePlant plantTile = (TilePlant) tile;
		
		plantTile.setStats(getPotency(stack), getYield(stack), getGrowth(stack));
		
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
