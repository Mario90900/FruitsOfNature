package com.mario90900.fruitsofnature.handler;

import java.io.File;

import com.mario90900.fruitsofnature.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	public static Configuration config;
	public static Boolean testValue;
	
	public static void init(File configFile){
		// Create the configuration object from the given configFile
		if(config == null){
			config = new Configuration(configFile);
			loadConfig();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID)){
			// Resync Configs
			loadConfig();
		}
	}
	
	private static void loadConfig(){
		testValue = config.getBoolean("configValue", Configuration.CATEGORY_GENERAL, true, "This is a test config value");
		
		if(config.hasChanged()){
			config.save();
		}
	}
}
