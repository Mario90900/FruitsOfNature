package com.mario90900.fruitsofnature;

import com.mario90900.fruitsofnature.handler.ConfigHandler;
import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.init.ModRecipes;
import com.mario90900.fruitsofnature.proxy.IProxy;
import com.mario90900.fruitsofnature.reference.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)
public class FruitsOfNature {
	
	@Mod.Instance(Reference.MOD_ID)
	public static FruitsOfNature instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		// Do load configs, init network handle, keybinds, init blocks + items
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		
		ModItems.init();
		
		ModBlocks.init();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		// Do setup GUI handle, Tile Ents, and General Event Handlers, and recipes
		proxy.registerTileEntities();
		
		ModRecipes.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		// Do wrap things up, and mod integration
		
	}
}
