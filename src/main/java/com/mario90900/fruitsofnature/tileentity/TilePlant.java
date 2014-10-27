package com.mario90900.fruitsofnature.tileentity;

import java.util.Random;

import com.mario90900.fruitsofnature.reference.PlantStats;
import com.mario90900.fruitsofnature.utility.NBTHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

/*
 * The Basic TileEntity for any growing plant block.
 * Holds the stats (Both the int value 0-4 and the float percentage)
 * of the growing plant here.
 */
public abstract class TilePlant extends TileEntityFON implements IStatTilePlant{
	protected float potency;
	protected int potencyInt;
	protected float yield;
	protected int yieldInt;
	protected float growth;
	protected int growthInt;
	
	public TilePlant(){
		super();
		potency = 0;
		potencyInt = 0;
		yield = 0;
		yieldInt = 0;
		growth = 0;
		growthInt = 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		if (compound.hasKey("Potency")) {
			potency = compound.getFloat("Potency");
		}
		if (compound.hasKey("Yield")) {
			yield = compound.getFloat("Yield");
		}
		if (compound.hasKey("Growth")) {
			growth = compound.getFloat("Growth");
		}
		
		if (compound.hasKey("PotencyInt")) {
			potencyInt = compound.getInteger("PotencyInt");
		}
		if (compound.hasKey("YieldInt")) {
			yieldInt = compound.getInteger("YieldInt");
		}
		if (compound.hasKey("GrowthInt")) {
			growthInt = compound.getInteger("GrowthInt");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		compound.setFloat("Potency", potency);
		compound.setFloat("Yield", yield);
		compound.setFloat("Growth", growth);
		
		compound.setInteger("PotencyInt", potencyInt);
		compound.setInteger("YieldInt", yieldInt);
		compound.setInteger("GrowthInt", growthInt);
	}
	
	public void setStats(int pot, int yld, int grow){
		switch (pot) {
		case 0:
			potency = PlantStats.VERYLOW_MOD;
			break;
		case 1:
			potency = PlantStats.LOW_MOD;
			break;
		case 2:
			potency = PlantStats.AVERAGE_MOD;
			break;
		case 3:
			potency = PlantStats.HIGH_MOD;
			break;
		case 4:
			potency = PlantStats.VERYHIGH_MOD;
			break;
		default:
			potency = PlantStats.VERYLOW_MOD;
		}
		
		switch (yld) {
		case 0:
			yield = PlantStats.VERYLOW_MOD;
			break;
		case 1:
			yield = PlantStats.LOW_MOD;
			break;
		case 2:
			yield = PlantStats.AVERAGE_MOD;
			break;
		case 3:
			yield = PlantStats.HIGH_MOD;
			break;
		case 4:
			yield = PlantStats.VERYHIGH_MOD;
			break;
		default:
			yield = PlantStats.VERYLOW_MOD;
		}
		
		switch (grow) {
		case 0:
			growth = PlantStats.VERYLOW_MOD;
			break;
		case 1:
			growth = PlantStats.LOW_MOD;
			break;
		case 2:
			growth = PlantStats.AVERAGE_MOD;
			break;
		case 3:
			growth = PlantStats.HIGH_MOD;
			break;
		case 4:
			growth = PlantStats.VERYHIGH_MOD;
			break;
		default:
			growth = PlantStats.VERYLOW_MOD;
		}
		
		potencyInt = pot;
		yieldInt = yld;
		growthInt = grow;
	}
	
	public int calcYield(Random rand, float avgYield){
		float numDropFloat = avgYield * getYield();
		int numDrops = (int) Math.floor(numDropFloat);
		float numDropsRemain = numDropFloat - numDrops;
		
		if (numDropsRemain == 0){
			return numDrops;
		} else {
			numDrops = numDrops + PlantHelper.calcYieldRemainder(rand, numDropsRemain);
			return numDrops;
		}
	}
	
	public int calcYieldRange(Random rand, float avgMin, float avgMax){
		int min = calcYield(rand, avgMin);
		int max = calcYield(rand, avgMax);
		
		if (min > max){
			return min;
		}
		
		return MathHelper.getRandomIntegerInRange(rand, min, max);
	}
	
	public float getPotency(){
		return potency;
	}
	
	public float getYield(){
		return yield;
	}
	
	public float getGrowth(){
		return growth;
	}
	
	public int getPotencyInt(){
		return potencyInt;
	}
	
	public int getYieldInt(){
		return yieldInt;
	}
	
	public int getGrowthInt(){
		return growthInt;
	}
	
	@Override
	public boolean canUpdate() {
		return false;
	}
}
