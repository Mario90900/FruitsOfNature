package com.mario90900.fruitsofnature.proxy;

import com.mario90900.fruitsofnature.client.render.FONBlockRenderers;
import com.mario90900.fruitsofnature.reference.RenderIds;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderers(){
		RenderIds.RenderLilypad = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderLilypad, new FONBlockRenderers());
		RenderIds.RenderVine = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderVine, new FONBlockRenderers());
		RenderIds.RenderPumpkinVine = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderPumpkinVine, new FONBlockRenderers());
		RenderIds.RenderMelonVine = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderMelonVine, new FONBlockRenderers());
		RenderIds.RenderCocoa = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(RenderIds.RenderCocoa, new FONBlockRenderers());
	}
}