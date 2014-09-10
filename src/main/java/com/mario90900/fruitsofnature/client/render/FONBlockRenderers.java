package com.mario90900.fruitsofnature.client.render;

import com.mario90900.fruitsofnature.reference.RenderIds;

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

        float f = 0.015625F;
        double d0 = (double)iicon.getMinU();
        double d1 = (double)iicon.getMinV();
        double d2 = (double)iicon.getMaxU();
        double d3 = (double)iicon.getMaxV();
        long l = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
        l = l * l * 42317861L + l * 11L;
        int i1 = (int)(l >> 16 & 3L);
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f1 = (float)y + 0.5F;
        float f2 = (float)z + 0.5F;
        float f3 = (float)(i1 & 1) * 0.5F * (float)(1 - i1 / 2 % 2 * 2);
        float f4 = (float)(i1 + 1 & 1) * 0.5F * (float)(1 - (i1 + 1) / 2 % 2 * 2);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 - f4), (double)((float)y + f), (double)(f2 + f3 - f4), d0, d3);
        tessellator.addVertexWithUV((double)(f1 - f3 + f4), (double)((float)y + f), (double)(f2 - f3 - f4), d2, d3);
        tessellator.addVertexWithUV((double)(f1 + f3 + f4), (double)((float)y + f), (double)(f2 - f3 + f4), d2, d1);
        tessellator.addVertexWithUV((double)(f1 + f3 - f4), (double)((float)y + f), (double)(f2 + f3 + f4), d0, d1);
        return true;
	}
}
