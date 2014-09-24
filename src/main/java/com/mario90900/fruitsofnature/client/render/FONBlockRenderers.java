package com.mario90900.fruitsofnature.client.render;

import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
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
		} else if (modelId == RenderIds.RenderVine){
			return renderWallVine(world, x, y, z, block, renderer);
		} else if (modelId == RenderIds.RenderPumpkinVine){
			return renderPumpkinVineGround(world, x, y, z, block, renderer);
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

        double minU = (double)iicon.getMinU(); //U is the horizontal coordinate of the texture, starting with 0 at the left
        double minV = (double)iicon.getMinV(); //V is the vertical coordinate of the texture, starting with 0 at the top
        double maxU = (double)iicon.getMaxU(); //Max U is the rightmost value
        double maxV = (double)iicon.getMaxV(); //Max V is the bottommost value
        float nudge = 0.015625f;
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        //Draws the bottom of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge, z, minU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z, maxU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x, y + nudge, z + 1, minU, maxV);
        
        //Draws the top of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge, z, minU, minV);
        tessellator.addVertexWithUV(x, y + nudge, z + 1, minU, maxV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x + 1, y + nudge, z, maxU, minV);
        return true;
	}
	
	protected boolean renderWallVine(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 1, world.getBlockMetadata(x, y, z));

        double minU = (double)iicon.getMinU();
        double minV = (double)iicon.getMinV();
        double maxU = (double)iicon.getMaxU();
        double maxV = (double)iicon.getMaxV();
        float nudge = 0.015625f;
        float neganudge = 1 - nudge;
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        if (world.getBlock(x, y, z + 1).isNormalCube()){ //Render South faces if solid face is present
        	//Draw the side facing the solid face
        	tessellator.addVertexWithUV(x, y + 1, z + neganudge, maxU, minV);
        	tessellator.addVertexWithUV(x, y, z + neganudge, maxU, maxV);
        	tessellator.addVertexWithUV(x + 1, y, z + neganudge, minU, maxV);
        	tessellator.addVertexWithUV(x + 1, y + 1, z + neganudge, minU, minV);
        	
        	//Draw the side facing the center of the vine block
        	tessellator.addVertexWithUV(x, y + 1, z + neganudge, maxU, minV);
        	tessellator.addVertexWithUV(x + 1, y + 1, z + neganudge, minU, minV);
        	tessellator.addVertexWithUV(x + 1, y, z + neganudge, minU, maxV);
        	tessellator.addVertexWithUV(x, y, z + neganudge, maxU, maxV);
		}
        if (world.getBlock(x - 1, y, z).isNormalCube()){ //Render West faces if solid face is present
			//Draw the side facing the solid face
        	tessellator.addVertexWithUV(x + nudge, y + 1, z, maxU, minV);
        	tessellator.addVertexWithUV(x + nudge, y, z, maxU, maxV);
        	tessellator.addVertexWithUV(x + nudge, y, z + 1, minU, maxV);
        	tessellator.addVertexWithUV(x + nudge, y + 1, z + 1, minU, minV);
        	
        	//Draw the side facing the center of the vine block
        	tessellator.addVertexWithUV(x + nudge, y + 1, z, maxU, minV);
        	tessellator.addVertexWithUV(x + nudge, y + 1, z + 1, minU, minV);
        	tessellator.addVertexWithUV(x + nudge, y, z + 1, minU, maxV);
        	tessellator.addVertexWithUV(x + nudge, y, z, maxU, maxV);
		}
        if (world.getBlock(x, y, z - 1).isNormalCube()){ //Render North faces if solid face is present
        	//Draw the side facing the solid face
        	tessellator.addVertexWithUV(x, y + 1, z + nudge, minU, minV);
        	tessellator.addVertexWithUV(x + 1, y + 1, z + nudge, maxU, minV);
        	tessellator.addVertexWithUV(x + 1, y, z + nudge, maxU, maxV);
        	tessellator.addVertexWithUV(x, y, z + nudge, minU, maxV);
        	
        	//Draw the side facing the center of the vine block
        	tessellator.addVertexWithUV(x, y + 1, z + nudge, minU, minV);
        	tessellator.addVertexWithUV(x, y, z + nudge, minU, maxV);
        	tessellator.addVertexWithUV(x + 1, y, z + nudge, maxU, maxV);
        	tessellator.addVertexWithUV(x + 1, y + 1, z + nudge, maxU, minV);
		}
        if (world.getBlock(x + 1, y, z).isNormalCube()){ //Render East faces if solid face is present
        	//Draw the side facing the solid face
        	tessellator.addVertexWithUV(x + neganudge, y + 1, z, minU, minV);
        	tessellator.addVertexWithUV(x + neganudge, y + 1, z + 1, maxU, minV);
        	tessellator.addVertexWithUV(x + neganudge, y, z + 1, maxU, maxV);
        	tessellator.addVertexWithUV(x + neganudge, y, z, minU, maxV);
        	
        	//Draw the side facing the center of the vine block
        	tessellator.addVertexWithUV(x + neganudge, y + 1, z, minU, minV);
        	tessellator.addVertexWithUV(x + neganudge, y, z, minU, maxV);
        	tessellator.addVertexWithUV(x + neganudge, y, z + 1, maxU, maxV);
        	tessellator.addVertexWithUV(x + neganudge, y + 1, z + 1, maxU, minV);
		}
        return true;
	}
	
	protected boolean renderPumpkinVineGround(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 1, world.getBlockMetadata(x, y, z));
        
        double minU = (double)iicon.getMinU();
        double minV = (double)iicon.getMinV();
        double maxU = (double)iicon.getMaxU();
        double maxV = (double)iicon.getMaxV();
        double halfV = (double)iicon.getInterpolatedV(8);
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        tessellator.addVertexWithUV(x, y, z, minU, maxV);
        tessellator.addVertexWithUV(x + 1, y, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x + 1, y + 0.5, z + 1, maxU, halfV);
        tessellator.addVertexWithUV(x, y + 0.5, z, minU, halfV);
        
        tessellator.addVertexWithUV(x + 1, y, z + 1, minU, maxV);
        tessellator.addVertexWithUV(x, y, z, maxU, maxV);
        tessellator.addVertexWithUV(x, y + 0.5, z, maxU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 0.5, z + 1, minU, halfV);
        
        tessellator.addVertexWithUV(x, y, z + 1, minU, maxV);
        tessellator.addVertexWithUV(x + 1, y, z, maxU, maxV);
        tessellator.addVertexWithUV(x + 1, y + 0.5, z, maxU, halfV);
        tessellator.addVertexWithUV(x, y + 0.5, z + 1, minU, halfV);
        
        tessellator.addVertexWithUV(x + 1, y, z, minU, maxV);
        tessellator.addVertexWithUV(x, y, z + 1, maxU, maxV);
        tessellator.addVertexWithUV(x, y + 0.5, z + 1, maxU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 0.5, z, minU, halfV);
        
        if (world.getBlockMetadata(x, y, z) == 3){
        	if (world.getBlock(x, y, z + 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z + 1) >= 4){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, minU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x + 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z - 1) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, minU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x - 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, halfV);
        	}
        } else if (world.getBlockMetadata(x, y, z) >= 4){
        	if (world.getBlock(x, y, z + 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z + 1) <= 3){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, minU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x + 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z - 1) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, minU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x - 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, halfV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, halfV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, minU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, minU, halfV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, minU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, halfV);
        	}
        }
		return true;
	}
}
