package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.item.ItemFON;
import com.mario90900.fruitsofnature.item.ItemFONFood;
import com.mario90900.fruitsofnature.item.ItemHunger;
import com.mario90900.fruitsofnature.item.crops.ItemBrownMushCrop;
import com.mario90900.fruitsofnature.item.crops.ItemCarrotCrop;
import com.mario90900.fruitsofnature.item.crops.ItemCocoaCrop;
import com.mario90900.fruitsofnature.item.crops.ItemMelonCrop;
import com.mario90900.fruitsofnature.item.crops.ItemPotatoCrop;
import com.mario90900.fruitsofnature.item.crops.ItemPumpkinCrop;
import com.mario90900.fruitsofnature.item.crops.ItemRedMushCrop;
import com.mario90900.fruitsofnature.item.crops.ItemWheatCrop;
import com.mario90900.fruitsofnature.item.seeds.ItemBrownMushSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemCarrotSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemCocoaSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemLilypadSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemMelonSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemPotatoSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemPumpkinSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemRedMushSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemVineSeeds;
import com.mario90900.fruitsofnature.item.seeds.ItemWheatSeeds;
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
	public static final ItemFON pumpkinSeeds = new ItemPumpkinSeeds();
	public static final ItemFON pumpkinCrop = new ItemPumpkinCrop();
	public static final ItemFON melonSeeds = new ItemMelonSeeds();
	public static final ItemFON melonCrop = new ItemMelonCrop();
	public static final ItemFON redMushSeeds = new ItemRedMushSeeds();
	public static final ItemFONFood redMushCrop = new ItemRedMushCrop();
	public static final ItemFON brownMushSeeds = new ItemBrownMushSeeds();
	public static final ItemFONFood brownMushCrop = new ItemBrownMushCrop();
	public static final ItemFON cocoaSeeds = new ItemCocoaSeeds();
	public static final ItemFON cocoaCrop = new ItemCocoaCrop();
	
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
		GameRegistry.registerItem(pumpkinSeeds, UnlocalizedNames.PUMPKIN_SEEDS);
		GameRegistry.registerItem(pumpkinCrop, UnlocalizedNames.PUMPKIN_CROP);
		GameRegistry.registerItem(melonSeeds, UnlocalizedNames.MELON_SEEDS);
		GameRegistry.registerItem(melonCrop, UnlocalizedNames.MELON_CROP);
		GameRegistry.registerItem(redMushSeeds, UnlocalizedNames.RED_MUSH_SEEDS);
		GameRegistry.registerItem(redMushCrop, UnlocalizedNames.RED_MUSH_CROP);
		GameRegistry.registerItem(brownMushSeeds, UnlocalizedNames.BROWN_MUSH_SEEDS);
		GameRegistry.registerItem(brownMushCrop, UnlocalizedNames.BROWN_MUSH_CROP);
		GameRegistry.registerItem(cocoaSeeds, UnlocalizedNames.COCOA_SEEDS);
		GameRegistry.registerItem(cocoaCrop, UnlocalizedNames.COCOA_CROP);
		
		//Other Items!
		GameRegistry.registerItem(hunger, UnlocalizedNames.HUNGER);
	}
}
