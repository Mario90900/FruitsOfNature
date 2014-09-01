package com.mario90900.fruitsofnature.item;

import com.mario90900.fruitsofnature.reference.FoodStats;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

public class ItemHunger extends ItemFONFood{
	public ItemHunger(){
		super(FoodStats.HUNGER_HEAL, FoodStats.HUNGER_SATURATION);
		this.setUnlocalizedName(UnlocalizedNames.HUNGER);
		this.setAlwaysEdible();
	}
}
