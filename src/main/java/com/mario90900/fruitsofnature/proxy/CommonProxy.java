package com.mario90900.fruitsofnature.proxy;

import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityWheatPlant;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class CommonProxy implements IProxy{
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityWheatPlant.class, UnlocalizedNames.WHEAT_PLANT);
	}
}
