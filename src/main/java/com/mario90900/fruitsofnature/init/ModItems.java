package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.item.ItemCarrotCrop;
import com.mario90900.fruitsofnature.item.ItemCarrotSeeds;
import com.mario90900.fruitsofnature.item.ItemFON;
import com.mario90900.fruitsofnature.item.ItemFONFood;
import com.mario90900.fruitsofnature.item.ItemHunger;
import com.mario90900.fruitsofnature.item.ItemLilypadSeeds;
import com.mario90900.fruitsofnature.item.ItemPotatoCrop;
import com.mario90900.fruitsofnature.item.ItemPotatoSeeds;
import com.mario90900.fruitsofnature.item.ItemVineSeeds;
import com.mario90900.fruitsofnature.item.ItemWheatCrop;
import com.mario90900.fruitsofnature.item.ItemWheatSeeds;
import com.mario90900.fruitsofnature.reference.Reference;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
	//Plant Seeds and Crops here!
	public static final ItemFON wheatSeeds = new ItemWheatSeeds();
	public static final ItemFON wheatCrop = new ItemWheatCrop();
	public static final ItemFON potatoSeeds = new ItemPotatoSeeds();
	public static final ItemFONFood potatoCrop = new ItemPotatoCrop();
	public static final ItemFON carrotSeeds = new ItemCarrotSeeds();
	public static final ItemFONFood carrotCrop = new ItemCarrotCrop();
	public static final ItemFON lilypadSeeds = new ItemLilypadSeeds();
	public static final ItemFON vineSeeds = new ItemVineSeeds();
	
	//Other Items!
	public static final ItemFONFood hunger = new ItemHunger();
	
	public static void init(){
		//Plant Seeds and Crops here!
		GameRegistry.registerItem(wheatSeeds, UnlocalizedNames.WHEAT_SEEDS);
		GameRegistry.registerItem(wheatCrop, UnlocalizedNames.WHEAT_CROP);
		GameRegistry.registerItem(potatoSeeds, UnlocalizedNames.POTATO_SEEDS);
		GameRegistry.registerItem(potatoCrop, UnlocalizedNames.POTATO_CROP);
		GameRegistry.registerItem(carrotSeeds, UnlocalizedNames.CARROT_SEEDS);
		GameRegistry.registerItem(carrotCrop, UnlocalizedNames.CARROT_CROP);
		GameRegistry.registerItem(lilypadSeeds, UnlocalizedNames.LILYPAD_SEEDS);
		GameRegistry.registerItem(vineSeeds, UnlocalizedNames.VINE_SEEDS);
		
		//Other Items!
		GameRegistry.registerItem(hunger, UnlocalizedNames.HUNGER);
	}
}
