package com.mario90900.fruitsofnature.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.mario90900.fruitsofnature.utility.PlantHelper;
import com.mario90900.fruitsofnature.utility.StringUtil;

public class ItemStatedFood extends ItemFONFood{
	public ItemStatedFood(int heal, float saturation){
		super(heal, saturation);
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
	}
	
	public ItemStatedFood(int heal){
		this(heal, 0.6f);
	}
	
	@Override //Adding the information on the Potency, Yield, and Growth to the tooltip
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag){
		String potency;
		
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
		
		list.add(StringUtil.localize("plants.stats.names.potency") + ": " + potency);
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
