package com.mario90900.fruitsofnature.client.render;

import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
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
		if (modelId == RenderIds.RenderLilypad){
			return renderLilypad(world, x, y, z, block, renderer);
		} else {
			return false;
		}
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

	protected boolean renderLilypad(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 1, world.getBlockMetadata(x, y, z));

        double minU = (double)iicon.getMinU();
        double minV = (double)iicon.getMinV();
        double maxU = (double)iicon.getMaxU();
        double maxV = (double)iicon.getMaxV();
        float nudge = 0.015625f;
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        //Draws the bottom of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge, z, minU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z, minU, maxV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x, y + nudge, z + 1, maxU, minV);
        
        //Draws the top of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge, z, minU, minV);
        tessellator.addVertexWithUV(x, y + nudge, z + 1, maxU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z, minU, maxV);
        return true;
	}
}
