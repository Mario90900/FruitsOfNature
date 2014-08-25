package com.mario90900.fruitsofnature.init;

import com.mario90900.fruitsofnature.block.BlockFON;
import com.mario90900.fruitsofnature.block.BlockWheat;
import com.mario90900.fruitsofnature.reference.Reference;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
	public static final BlockFON wheatPlant = new BlockWheat();
	
	public static void init(){
		GameRegistry.registerBlock(wheatPlant, UnlocalizedNames.WHEAT_PLANT);
	}
}