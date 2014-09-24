package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.block.BlockCarrot;
import com.mario90900.fruitsofnature.block.BlockFON;
import com.mario90900.fruitsofnature.block.BlockLilypad;
import com.mario90900.fruitsofnature.block.BlockPotato;
import com.mario90900.fruitsofnature.block.BlockPumpkin;
import com.mario90900.fruitsofnature.block.BlockVine;
import com.mario90900.fruitsofnature.block.BlockWheat;
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
	
	public static void init(){
		GameRegistry.registerBlock(wheatPlant, UnlocalizedNames.WHEAT_PLANT);
		GameRegistry.registerBlock(potatoPlant, UnlocalizedNames.POTATO_PLANT);
		GameRegistry.registerBlock(carrotPlant, UnlocalizedNames.CARROT_PLANT);
		GameRegistry.registerBlock(lilypadPlant, UnlocalizedNames.LILYPAD_PLANT);
		GameRegistry.registerBlock(vinePlant, UnlocalizedNames.VINE_PLANT);
		GameRegistry.registerBlock(pumpkinPlant, UnlocalizedNames.PUMPKIN_PLANT);
	}
}
