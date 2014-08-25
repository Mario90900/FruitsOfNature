package com.mario90900.fruitsofnature.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.utility.NBTHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;
import com.mario90900.fruitsofnature.utility.StringUtil;

/* 
 * The base class for any modded seed that will have stats
 * Potency effects the strength of the crop's effects
 * Yield effects the amount harvested
 * Growth effects the growth speed
 * Each stat operates on a percentage of the default value, and has a total of 5 levels
 * Very Low = 25% | Low = 50% | Average = 100% | High = 150% | Very High = 200%
 * Represented by an int for the levels, 0 = Very Low, up to 4 = Very High
 */
public class ItemStatedBase extends ItemFON{
	public ItemStatedBase(){
		super();
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
	}
	
	@Override //Adding the information on the Potency, Yield, and Growth to the tooltip
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag){
		String potency;
		String yield;
		String growth;
		int level = PlantHelper.getPotency(stack);
		switch(level){
		case 0:
			potency = StringUtil.localize("plants.stats.level.vlow");
			break;
		case 1:
			potency = StringUtil.localize("plants.stats.level.low");
			break;
		case 2:
			potency = StringUtil.localize("plants.stats.level.avg");
			break;
		case 3:
			potency = StringUtil.localize("plants.stats.level.high");
			break;
		case 4:
			potency = StringUtil.localize("plants.stats.level.vhigh");
			break;
		default:
			potency = StringUtil.localize("plants.stats.level.none");
			break;
		}
		level = PlantHelper.getYield(stack);
		switch(level){
		case 0:
			yield = StringUtil.localize("plants.stats.level.vlow");
			break;
		case 1:
			yield = StringUtil.localize("plants.stats.level.low");
			break;
		case 2:
			yield = StringUtil.localize("plants.stats.level.avg");
			break;
		case 3:
			yield = StringUtil.localize("plants.stats.level.high");
			break;
		case 4:
			yield = StringUtil.localize("plants.stats.level.vhigh");
			break;
		default:
			yield = StringUtil.localize("plants.stats.level.none");
			break;
		}
		level = PlantHelper.getGrowth(stack);
		switch(level){
		case 0:
			growth = StringUtil.localize("plants.stats.level.vlow");
			break;
		case 1:
			growth = StringUtil.localize("plants.stats.level.low");
			break;
		case 2:
			growth = StringUtil.localize("plants.stats.level.avg");
			break;
		case 3:
			growth = StringUtil.localize("plants.stats.level.high");
			break;
		case 4:
			growth = StringUtil.localize("plants.stats.level.vhigh");
			break;
		default:
			growth = StringUtil.localize("plants.stats.level.none");
			break;
		}
		list.add(StringUtil.localize("plants.stats.names.potency") + ": " + potency);
		list.add(StringUtil.localize("plants.stats.names.yield") + ": " + yield);
		list.add(StringUtil.localize("plants.stats.names.growth") + ": " + growth);
	}
	
	@Override
	public boolean isDamageable() {
		return false;
	}

	@Override
	public boolean isRepairable() {
		return false;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}
}
