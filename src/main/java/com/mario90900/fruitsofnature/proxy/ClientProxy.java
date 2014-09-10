package com.mario90900.fruitsofnature.proxy;

import com.mario90900.fruitsofnature.client.render.FONBlockRenderers;
import com.mario90900.fruitsofnature.reference.RenderIds;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderIds.RenderLilypad = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderLilypad, new FONBlockRenderers());
	}
}
