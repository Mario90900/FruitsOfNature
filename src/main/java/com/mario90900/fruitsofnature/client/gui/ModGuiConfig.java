package com.mario90900.fruitsofnature.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.mario90900.fruitsofnature.handler.ConfigHandler;
import com.mario90900.fruitsofnature.reference.Reference;

import cpw.mods.fml.client.config.GuiConfig;

public class ModGuiConfig extends GuiConfig{
	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
	}
}
