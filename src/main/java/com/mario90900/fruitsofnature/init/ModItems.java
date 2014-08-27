package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.item.ItemCarrotCrop;
import com.mario90900.fruitsofnature.item.ItemCarrotSeeds;
import com.mario90900.fruitsofnature.item.ItemFON;
import com.mario90900.fruitsofnature.item.ItemPotatoCrop;
import com.mario90900.fruitsofnature.item.ItemPotatoSeeds;
import com.mario90900.fruitsofnature.item.ItemWheatCrop;
import com.mario90900.fruitsofnature.item.ItemWheatSeeds;
import com.mario90900.fruitsofnature.reference.Reference;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	public static final ItemFON wheatSeeds = new ItemWheatSeeds();
	public static final ItemFON wheatCrop = new ItemWheatCrop();
	public static final ItemFON potatoSeeds = new ItemPotatoSeeds();
	public static final ItemFON potatoCrop = new ItemPotatoCrop();
	public static final ItemFON carrotSeeds = new ItemCarrotSeeds();
	public static final ItemFON carrotCrop = new ItemCarrotCrop();
	
	public static void init(){
		GameRegistry.registerItem(wheatSeeds, UnlocalizedNames.WHEAT_SEEDS);
		GameRegistry.registerItem(wheatCrop, UnlocalizedNames.WHEAT_CROP);
		GameRegistry.registerItem(potatoSeeds, UnlocalizedNames.POTATO_SEEDS);
		GameRegistry.registerItem(potatoCrop, UnlocalizedNames.POTATO_CROP);
		GameRegistry.registerItem(carrotSeeds, UnlocalizedNames.CARROT_SEEDS);
		GameRegistry.registerItem(carrotCrop, UnlocalizedNames.CARROT_CROP);
	}
}
