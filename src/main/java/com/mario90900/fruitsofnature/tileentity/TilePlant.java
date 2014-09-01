package com.mario90900.fruitsofnature.tileentity;

import com.mario90900.fruitsofnature.reference.AveragePlantStats;
import com.mario90900.fruitsofnature.utility.NBTHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/*
 * The Basic TileEntity for any growing plant block.
 * Holds the stats (Both the int value 0-4 and the float percentage)
 * of the growing plant here.
 */
public class TilePlant extends TileEntityFON {
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
			potency = AveragePlantStats.VERYLOW_MOD;
			break;
		case 1:
			potency = AveragePlantStats.LOW_MOD;
			break;
		case 2:
			potency = AveragePlantStats.AVERAGE_MOD;
			break;
		case 3:
			potency = AveragePlantStats.HIGH_MOD;
			break;
		case 4:
			potency = AveragePlantStats.VERYHIGH_MOD;
			break;
		default:
			potency = AveragePlantStats.VERYLOW_MOD;
		}
		
		switch (yld) {
		case 0:
			yield = AveragePlantStats.VERYLOW_MOD;
			break;
		case 1:
			yield = AveragePlantStats.LOW_MOD;
			break;
		case 2:
			yield = AveragePlantStats.AVERAGE_MOD;
			break;
		case 3:
			yield = AveragePlantStats.HIGH_MOD;
			break;
		case 4:
			yield = AveragePlantStats.VERYHIGH_MOD;
			break;
		default:
			yield = AveragePlantStats.VERYLOW_MOD;
		}
		
		switch (grow) {
		case 0:
			growth = AveragePlantStats.VERYLOW_MOD;
			break;
		case 1:
			growth = AveragePlantStats.LOW_MOD;
			break;
		case 2:
			growth = AveragePlantStats.AVERAGE_MOD;
			break;
		case 3:
			growth = AveragePlantStats.HIGH_MOD;
			break;
		case 4:
			growth = AveragePlantStats.VERYHIGH_MOD;
			break;
		default:
			growth = AveragePlantStats.VERYLOW_MOD;
		}
		
		potencyInt = pot;
		yieldInt = yld;
		growthInt = grow;
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
