package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.block.BlockFON;
import com.mario90900.fruitsofnature.block.plants.BlockBrownMush;
import com.mario90900.fruitsofnature.block.plants.BlockCarrot;
import com.mario90900.fruitsofnature.block.plants.BlockCocoa;
import com.mario90900.fruitsofnature.block.plants.BlockLilypad;
import com.mario90900.fruitsofnature.block.plants.BlockMelon;
import com.mario90900.fruitsofnature.block.plants.BlockPotato;
import com.mario90900.fruitsofnature.block.plants.BlockPumpkin;
import com.mario90900.fruitsofnature.block.plants.BlockRedMush;
import com.mario90900.fruitsofnature.block.plants.BlockVine;
import com.mario90900.fruitsofnature.block.plants.BlockWheat;
import com.mario90900.fruitsofnature.reference.Reference;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	public static final BlockFON wheatPlant = new BlockWheat();
	public static final BlockFON potatoPlant = new BlockPotato();
	public static final BlockFON carrotPlant = new BlockCarrot();
	public static final BlockFON lilypadPlant = new BlockLilypad();
	public static final BlockFON vinePlant = new BlockVine();
	public static final BlockFON pumpkinPlant = new BlockPumpkin();
	public static final BlockFON melonPlant = new BlockMelon();
	public static final BlockFON redMushPlant = new BlockRedMush();
	public static final BlockFON brownMushPlant = new BlockBrownMush();
	public static final BlockFON cocoaPlant = new BlockCocoa();
	
	public static void init(){
		GameRegistry.registerBlock(wheatPlant, UnlocalizedNames.WHEAT_PLANT);
		GameRegistry.registerBlock(potatoPlant, UnlocalizedNames.POTATO_PLANT);
		GameRegistry.registerBlock(carrotPlant, UnlocalizedNames.CARROT_PLANT);
		GameRegistry.registerBlock(lilypadPlant, UnlocalizedNames.LILYPAD_PLANT);
		GameRegistry.registerBlock(vinePlant, UnlocalizedNames.VINE_PLANT);
		GameRegistry.registerBlock(pumpkinPlant, UnlocalizedNames.PUMPKIN_PLANT);
		GameRegistry.registerBlock(melonPlant, UnlocalizedNames.MELON_PLANT);
		GameRegistry.registerBlock(redMushPlant, UnlocalizedNames.RED_MUSH_PLANT);
		GameRegistry.registerBlock(brownMushPlant, UnlocalizedNames.BROWN_MUSH_PLANT);
		GameRegistry.registerBlock(cocoaPlant, UnlocalizedNames.COCOA_PLANT);
	}
}
