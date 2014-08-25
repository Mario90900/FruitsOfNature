package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.item.ItemFON;
import com.mario90900.fruitsofnature.item.ItemWheatCrop;
import com.mario90900.fruitsofnature.item.ItemWheatSeeds;
import com.mario90900.fruitsofnature.reference.Reference;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static final ItemFON wheatSeeds = new ItemWheatSeeds();
	public static final ItemFON wheatCrop = new ItemWheatCrop();
	
	public static void init(){
		GameRegistry.registerItem(wheatSeeds, UnlocalizedNames.WHEAT_SEEDS);
		GameRegistry.registerItem(wheatCrop, UnlocalizedNames.WHEAT_CROP);
	}
}
