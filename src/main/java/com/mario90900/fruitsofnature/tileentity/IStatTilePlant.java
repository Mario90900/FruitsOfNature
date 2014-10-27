package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import net.minecraft.world.World;

public interface IStatTilePlant {
	
	public void onBlockTick(World world, int x, int y, int z, Random rand);
	public int calcYield(Random rand);
	
	public int getPotencyInt();
	public int getYieldInt();
	public int getGrowthInt();
}
