package com.mario90900.fruitsofnature.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FONBlockRenderers implements ISimpleBlockRenderingHandler{

	/*
	 * Quick Reference notes for myself:
	 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/1430214-solved-forge-1-5-x-special-block-custom-rendering
	 * Also check RenderBlocks.class and ISimpleBlockRenderingHandler.class
	 * Use already utilized vanilla rendering methods to make my own!
	 */
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		//May be used later, but for all plants, as they never intend to be picked up as a block, this can be ignored.
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}

}
