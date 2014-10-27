package com.mario90900.fruitsofnature.proxy;

import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityBrownMushPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityCarrotPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityCocoaPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityLilypadPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityMelonPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityPotatoPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityPumpkinPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityRedMushPlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityVinePlant;
import com.mario90900.fruitsofnature.tileentity.TileEntityWheatPlant;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy{
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityWheatPlant.class, UnlocalizedNames.WHEAT_PLANT);
		GameRegistry.registerTileEntity(TileEntityPotatoPlant.class, UnlocalizedNames.POTATO_PLANT);
		GameRegistry.registerTileEntity(TileEntityCarrotPlant.class, UnlocalizedNames.CARROT_PLANT);
		GameRegistry.registerTileEntity(TileEntityLilypadPlant.class, UnlocalizedNames.LILYPAD_PLANT);
		GameRegistry.registerTileEntity(TileEntityVinePlant.class, UnlocalizedNames.VINE_PLANT);
		GameRegistry.registerTileEntity(TileEntityPumpkinPlant.class, UnlocalizedNames.PUMPKIN_PLANT);
		GameRegistry.registerTileEntity(TileEntityMelonPlant.class, UnlocalizedNames.MELON_PLANT);
		GameRegistry.registerTileEntity(TileEntityRedMushPlant.class, UnlocalizedNames.RED_MUSH_PLANT);
		GameRegistry.registerTileEntity(TileEntityBrownMushPlant.class, UnlocalizedNames.BROWN_MUSH_PLANT);
		GameRegistry.registerTileEntity(TileEntityCocoaPlant.class, UnlocalizedNames.COCOA_PLANT);
	}
}