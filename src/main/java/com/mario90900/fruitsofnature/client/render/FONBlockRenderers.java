package com.mario90900.fruitsofnature.client.render;

import com.mario90900.fruitsofnature.block.plants.BlockLogPlant;
import com.mario90900.fruitsofnature.init.ModBlocks;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.utility.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
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
		} else if (modelId == RenderIds.RenderMelonVine){
			return renderMelonVineGround(world, x, y, z, block, renderer);
		} else if (modelId == RenderIds.RenderCocoa){
			return renderCocoa(world, x, y, z, block, renderer);
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
        double halfU = (double)iicon.getInterpolatedU(8); //The halfway point from the left to the right
        double halfV = (double)iicon.getInterpolatedV(8); //The halfway point from the top to the bottom
        double maxU = (double)iicon.getMaxU(); //Max U is the rightmost value
        double maxV = (double)iicon.getMaxV(); //Max V is the bottommost value
        float nudge = 0.016f;
        float eigth = 0.125f;
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        //Draws the bottom of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge - eigth, z, minU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge - eigth, z, halfU, minV);
        tessellator.addVertexWithUV(x + 1, y + nudge - eigth, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x, y + nudge - eigth, z + 1, minU, halfV);
        
        //Draws the top of the Lilypad Plant
        tessellator.addVertexWithUV(x, y + nudge - eigth, z, minU, minV);
        tessellator.addVertexWithUV(x, y + nudge - eigth, z + 1, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y + nudge - eigth, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y + nudge - eigth, z, halfU, minV);
        
        //Draws the bottom of the Lilypad Roots
        tessellator.addVertexWithUV(x, y - nudge - eigth, z, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y - nudge - eigth, z, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y - nudge - eigth, z + 1, halfU, maxV);
        tessellator.addVertexWithUV(x, y - nudge - eigth, z + 1, minU, maxV);
        
        //Draws the top of the Lilypad Roots
        tessellator.addVertexWithUV(x, y - nudge - eigth, z, minU, halfV);
        tessellator.addVertexWithUV(x, y - nudge - eigth, z + 1, minU, maxV);
        tessellator.addVertexWithUV(x + 1, y - nudge - eigth, z + 1, halfU, maxV);
        tessellator.addVertexWithUV(x + 1, y - nudge - eigth, z, halfU, halfV);
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
        double halfU = (double)iicon.getInterpolatedU(8);
        double quartV = (double)iicon.getInterpolatedV(4);
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        tessellator.addVertexWithUV(x, y, z, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 1, z + 1, halfU, minV);
        tessellator.addVertexWithUV(x, y + 1, z, minU, minV);
        
        tessellator.addVertexWithUV(x + 1, y, z + 1, minU, halfV);
        tessellator.addVertexWithUV(x, y, z, halfU, halfV);
        tessellator.addVertexWithUV(x, y + 1, z, halfU, minV);
        tessellator.addVertexWithUV(x + 1, y + 1, z + 1, minU, minV);
        
        tessellator.addVertexWithUV(x, y, z + 1, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y, z, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 1, z, halfU, minV);
        tessellator.addVertexWithUV(x, y + 1, z + 1, minU, minV);
        
        tessellator.addVertexWithUV(x + 1, y, z, minU, halfV);
        tessellator.addVertexWithUV(x, y, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x, y + 1, z + 1, halfU, minV);
        tessellator.addVertexWithUV(x + 1, y + 1, z, minU, minV);
        
        if (world.getBlockMetadata(x, y, z) == 3){ //This will handle the various ways the "connecting vine" will point, otherwise it points upwards. For Meta 5+, also handles the cubic pumpkin.
        	if (world.getBlock(x, y, z + 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z + 1) >= 4){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x + 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z - 1) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x - 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        	}
        } else if (world.getBlockMetadata(x, y, z) >= 4){
        	if (world.getBlock(x, y, z + 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z + 1) <= 3){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x + 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x, y, z - 1) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.pumpkinPlant && world.getBlockMetadata(x - 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        	}
        	
        	if (world.getBlockMetadata(x, y, z) == 5){ //This handles the various solid pumpkins growing
        		double sideU = (double)iicon.getInterpolatedU(3);
        		double sideV = (double)iicon.getInterpolatedV(10);
        		double topU = (double)iicon.getInterpolatedU(11);
        		double topV = (double)iicon.getInterpolatedV(11);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, halfU, topV);
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, topU, topV);
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, minU, halfV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, minU, sideV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, minU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, minU, sideV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, minU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, minU, sideV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, minU, halfV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, minU, sideV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, halfU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, halfU, topV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, topU, topV);
        	} else if (world.getBlockMetadata(x, y, z) == 6){
        		double sideU = (double)iicon.getInterpolatedU(3.5);
        		double sideV = (double)iicon.getInterpolatedV(10.5);
        		double topU = (double)iicon.getInterpolatedU(11.5);
        		double topV = (double)iicon.getInterpolatedV(11.5);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, halfU, topV);
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, topU, topV);
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, minU, halfV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, minU, sideV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, minU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, minU, sideV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, minU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, minU, sideV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, minU, halfV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, minU, sideV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, halfU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, halfU, topV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, topU, topV);
        	} else if (world.getBlockMetadata(x, y, z) == 7){
        		double sideU = (double)iicon.getInterpolatedU(4);
        		double sideV = (double)iicon.getInterpolatedV(11);
        		double topU = (double)iicon.getInterpolatedU(12);
        		double topV = (double)iicon.getInterpolatedV(12);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, halfU, topV);
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, topU, topV);
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, minU, halfV);
        		tessellator.addVertexWithUV(x + .25, y, z + .25, minU, sideV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, minU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .25, minU, sideV);
        		tessellator.addVertexWithUV(x + .25, y, z + .25, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, minU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, minU, sideV);
        		tessellator.addVertexWithUV(x + .75, y, z + .25, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, minU, halfV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, minU, sideV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .25, y, z + .25, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .75, y, z + .25, halfU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, halfU, topV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, topU, topV);
        	}
        }
		return true;
	}
	
	protected boolean renderMelonVineGround(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 1, world.getBlockMetadata(x, y, z));
        
        double minU = (double)iicon.getMinU();
        double minV = (double)iicon.getMinV();
        double maxU = (double)iicon.getMaxU();
        double maxV = (double)iicon.getMaxV();
        double halfV = (double)iicon.getInterpolatedV(8);
        double halfU = (double)iicon.getInterpolatedU(8);
        double quartV = (double)iicon.getInterpolatedV(4);
        
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        
        tessellator.addVertexWithUV(x, y, z, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 1, z + 1, halfU, minV);
        tessellator.addVertexWithUV(x, y + 1, z, minU, minV);
        
        tessellator.addVertexWithUV(x + 1, y, z + 1, minU, halfV);
        tessellator.addVertexWithUV(x, y, z, halfU, halfV);
        tessellator.addVertexWithUV(x, y + 1, z, halfU, minV);
        tessellator.addVertexWithUV(x + 1, y + 1, z + 1, minU, minV);
        
        tessellator.addVertexWithUV(x, y, z + 1, minU, halfV);
        tessellator.addVertexWithUV(x + 1, y, z, halfU, halfV);
        tessellator.addVertexWithUV(x + 1, y + 1, z, halfU, minV);
        tessellator.addVertexWithUV(x, y + 1, z + 1, minU, minV);
        
        tessellator.addVertexWithUV(x + 1, y, z, minU, halfV);
        tessellator.addVertexWithUV(x, y, z + 1, halfU, halfV);
        tessellator.addVertexWithUV(x, y + 1, z + 1, halfU, minV);
        tessellator.addVertexWithUV(x + 1, y + 1, z, minU, minV);
        
        if (world.getBlockMetadata(x, y, z) == 3){ //This will handle the various ways the "connecting vine" will point, otherwise it points upwards. For Meta 5+, also handles the cubic pumpkin.
        	if (world.getBlock(x, y, z + 1) == ModBlocks.melonPlant && world.getBlockMetadata(x, y, z + 1) >= 4){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.melonPlant && world.getBlockMetadata(x + 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.melonPlant && world.getBlockMetadata(x, y, z - 1) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.melonPlant && world.getBlockMetadata(x - 1, y, z) >= 4) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        	}
        } else if (world.getBlockMetadata(x, y, z) >= 4){
        	if (world.getBlock(x, y, z + 1) == ModBlocks.melonPlant && world.getBlockMetadata(x, y, z + 1) <= 3){
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x + 1, y, z) == ModBlocks.melonPlant && world.getBlockMetadata(x + 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x, y, z - 1) == ModBlocks.melonPlant && world.getBlockMetadata(x, y, z - 1) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y, z, halfU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else if (world.getBlock(x - 1, y, z) == ModBlocks.melonPlant && world.getBlockMetadata(x - 1, y, z) <= 3) {
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, maxU, quartV);
        	} else {
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		
        		tessellator.addVertexWithUV(x, y + 0.5, z + 0.5, halfU, quartV);
        		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, maxU, minV);
        		tessellator.addVertexWithUV(x + 1, y + 0.5, z + 0.5, maxU, quartV);
        	}
        	
        	if (world.getBlockMetadata(x, y, z) == 5){ //This handles the various solid pumpkins growing
        		double sideU = (double)iicon.getInterpolatedU(3);
        		double sideV = (double)iicon.getInterpolatedV(10);
        		double topU = (double)iicon.getInterpolatedU(11);
        		double topV = (double)iicon.getInterpolatedV(11);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, halfU, topV);
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, topU, topV);
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, minU, halfV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, minU, sideV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .3125, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, minU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, minU, sideV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .3125, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, minU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, minU, sideV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .6875, y + .375, z + .6875, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .3125, y + .375, z + .6875, minU, halfV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, minU, sideV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .3125, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .3125, halfU, halfV);
        		tessellator.addVertexWithUV(x + .6875, y + .125, z + .6875, halfU, topV);
        		tessellator.addVertexWithUV(x + .3125, y + .125, z + .6875, topU, topV);
        	} else if (world.getBlockMetadata(x, y, z) == 6){
        		double sideU = (double)iicon.getInterpolatedU(3.5);
        		double sideV = (double)iicon.getInterpolatedV(10.5);
        		double topU = (double)iicon.getInterpolatedU(11.5);
        		double topV = (double)iicon.getInterpolatedV(11.5);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, halfU, topV);
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, topU, topV);
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, minU, halfV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, minU, sideV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .28125, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, minU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, minU, sideV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .28125, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, minU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, minU, sideV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .71875, y + .375, z + .71875, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .28125, y + .375, z + .71875, minU, halfV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, minU, sideV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .28125, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .28125, halfU, halfV);
        		tessellator.addVertexWithUV(x + .71875, y + .0625, z + .71875, halfU, topV);
        		tessellator.addVertexWithUV(x + .28125, y + .0625, z + .71875, topU, topV);
        	} else if (world.getBlockMetadata(x, y, z) == 7){
        		double sideU = (double)iicon.getInterpolatedU(4);
        		double sideV = (double)iicon.getInterpolatedV(11);
        		double topU = (double)iicon.getInterpolatedU(12);
        		double topV = (double)iicon.getInterpolatedV(12);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, halfU, halfV); //Start with the top of the pumpkin, same order for all three meta
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, halfU, topV);
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, topU, topV);
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, topU, halfV);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, sideU, halfV); //Side viewed from west to east
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, minU, halfV);
        		tessellator.addVertexWithUV(x + .25, y, z + .25, minU, sideV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .25, sideU, halfV); //Side viewed from north to south
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, minU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .25, minU, sideV);
        		tessellator.addVertexWithUV(x + .25, y, z + .25, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .25, sideU, halfV); //Side viewed from east to west
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, minU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, minU, sideV);
        		tessellator.addVertexWithUV(x + .75, y, z + .25, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .75, y + .375, z + .75, sideU, halfV); //Side viewed from south to north
        		tessellator.addVertexWithUV(x + .25, y + .375, z + .75, minU, halfV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, minU, sideV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, sideU, sideV);
        		
        		tessellator.addVertexWithUV(x + .25, y, z + .25, topU, halfV); //The bottom of the pumpkin
        		tessellator.addVertexWithUV(x + .75, y, z + .25, halfU, halfV);
        		tessellator.addVertexWithUV(x + .75, y, z + .75, halfU, topV);
        		tessellator.addVertexWithUV(x + .25, y, z + .75, topU, topV);
        	}
        }
		return true;
	}
	
	protected boolean renderCocoa(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer){
		Tessellator tessellator = Tessellator.instance;
        IIcon iicon = renderer.getBlockIconFromSideAndMetadata(block, 1, world.getBlockMetadata(x, y, z));
        
        double minU = (double)iicon.getMinU();
        double minV = (double)iicon.getMinV();
        double maxU = (double)iicon.getMaxU();
        double maxV = (double)iicon.getMaxV();
        double halfU = (double)iicon.getInterpolatedU(8);
        double halfV = (double)iicon.getInterpolatedV(8);
        double lastQuarterU = (double)iicon.getInterpolatedU(12);
        float nudge = 0.015625f;
        float neganudge = 1 - nudge;
        
        int meta = world.getBlockMetadata(x, y, z);
        double metaSeg;
        
        if (meta >= 3){
        	metaSeg = meta - 2;
        } else {
        	metaSeg = 0;
        }
		
        double sideU = (double)iicon.getInterpolatedU(.5*metaSeg);
        double sideV = (double)iicon.getInterpolatedV(8 + (1*metaSeg));
        double endsU = (double)iicon.getInterpolatedU(8 + (.5*metaSeg));
        double endsV = (double)iicon.getInterpolatedV(8 + (.5*metaSeg));
        
        double halfPixelSeg = 0.03125*metaSeg; //Equivalent to half 
        double pixelSeg = 0.0625*metaSeg;
        double doublePixelSeg = 0.125*metaSeg;
        double twoPixels = 0.0625*2;
        
        int woodID = OreDictionary.getOreID("logWood");
        int[] sBlockIDs = BlockLogPlant.getBlockOreIds(world, x, y, z + 1);
		if (BlockLogPlant.isOreIDsEqual(sBlockIDs, woodID)){ //If log is to the South
			tessellator.addVertexWithUV(x, y, z + neganudge, halfU, halfV); //This creates the flat surface on the log
			tessellator.addVertexWithUV(x, y + 1, z + neganudge, halfU, minV);
			tessellator.addVertexWithUV(x + 1, y + 1, z + neganudge, minU, minV);
			tessellator.addVertexWithUV(x + 1, y, z + neganudge, minU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, halfV); //Creates one side of the stem hanging off
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, halfU, minV);
			
			tessellator.addVertexWithUV(x + 0.5, y, z + 1, halfU, halfV); //Creates the other side of the stem
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 1, halfU, minV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), halfU, halfV); //The top of the cocoa pod itself
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), halfU, endsV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), endsU, endsV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), endsU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), minU, halfV); //Side towards the log
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), minU, halfV); //Side to the left of the log (facing away)
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), minU, halfV); //Side facing away from the log
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels), minU, halfV); //Side to the right of the log (facing away)
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + (1 - twoPixels - pixelSeg), sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), halfU, halfV); //Bottom of the pod
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), endsU, halfV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels - pixelSeg), endsU, endsV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + (1 - twoPixels), halfU, endsV);
			return true;
		}
		
		int[] eBlockIDs = BlockLogPlant.getBlockOreIds(world, x + 1, y, z);
		if (BlockLogPlant.isOreIDsEqual(eBlockIDs, woodID)){ //If log is to the East
			tessellator.addVertexWithUV(x + neganudge, y, z, halfU, halfV); //This creates the flat surface on the log
			tessellator.addVertexWithUV(x + neganudge, y, z + 1, minU, halfV);
			tessellator.addVertexWithUV(x + neganudge, y + 1, z + 1, minU, minV);
			tessellator.addVertexWithUV(x + neganudge, y + 1, z, halfU, minV);
			
			tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, halfV); //Creates one side of the stem hanging off
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, halfU, minV);
			
			tessellator.addVertexWithUV(x + 1, y, z + 0.5, halfU, halfV); //Creates the other side of the stem
			tessellator.addVertexWithUV(x + 1, y + 1, z + 0.5, halfU, minV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, halfU, halfV); //The top of the cocoa pod itself
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, halfU, endsV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, endsU, endsV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, endsU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, minU, halfV); //Side towards the log
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, minU, halfV); //Side to the left of the log (facing away)
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, minU, halfV); //Side facing away from the log
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 - halfPixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, minU, halfV); //Side to the right of the log (facing away)
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels), z + 0.5 + halfPixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, halfU, halfV); //Bottom of the pod
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, endsU, halfV);
			tessellator.addVertexWithUV(x + (1 - twoPixels - pixelSeg), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, endsU, endsV);
			tessellator.addVertexWithUV(x + (1 - twoPixels), y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, halfU, endsV);
			return true;
		}
		
		int[] nBlockIDs = BlockLogPlant.getBlockOreIds(world, x, y, z - 1);
		if (BlockLogPlant.isOreIDsEqual(nBlockIDs, woodID)){ //If log is to the North
			tessellator.addVertexWithUV(x, y, z + nudge, minU, halfV); //This creates the flat surface on the log
			tessellator.addVertexWithUV(x + 1, y, z + nudge, halfU, halfV);
			tessellator.addVertexWithUV(x + 1, y + 1, z + nudge, halfU, minV);
			tessellator.addVertexWithUV(x, y + 1, z + nudge, minU, minV);
			
			tessellator.addVertexWithUV(x + 0.5, y, z, halfU, halfV); //Creates one side of the stem hanging off
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z, halfU, minV);
			
			tessellator.addVertexWithUV(x + 0.5, y, z, halfU, halfV); //Creates the other side of the stem
			tessellator.addVertexWithUV(x + 0.5, y + 1, z, halfU, minV);
			tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
			tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels, halfU, halfV); //The top of the cocoa pod itself
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels, halfU, endsV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, endsU, endsV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, endsU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels, minU, halfV); //Side towards the log
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels, sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, minU, halfV); //Side to the left of the log (facing away)
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels, sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, minU, halfV); //Side facing away from the log
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels, minU, halfV); //Side to the right of the log (facing away)
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, minU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, sideU, sideV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels), z + twoPixels + pixelSeg, sideU, halfV);
			
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, halfU, halfV); //Bottom of the pod
			tessellator.addVertexWithUV(x + 0.5 + halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, endsU, halfV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels + pixelSeg, endsU, endsV);
			tessellator.addVertexWithUV(x + 0.5 - halfPixelSeg, y + (1 - twoPixels - doublePixelSeg), z + twoPixels, halfU, endsV);
			return true;
		}
		
		//Log is to the West
		tessellator.addVertexWithUV(x + nudge, y, z, minU, halfV); //This creates the flat surface on the log
		tessellator.addVertexWithUV(x + nudge, y + 1, z, minU, minV);
		tessellator.addVertexWithUV(x + nudge, y + 1, z + 1, halfU, minV);
		tessellator.addVertexWithUV(x + nudge, y, z + 1, halfU, halfV);
		
		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, halfV); //Creates one side of the stem hanging off
		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
		
		tessellator.addVertexWithUV(x, y, z + 0.5, halfU, halfV); //Creates the other side of the stem
		tessellator.addVertexWithUV(x, y + 1, z + 0.5, halfU, minV);
		tessellator.addVertexWithUV(x + 0.5, y + 1, z + 0.5, lastQuarterU, minV);
		tessellator.addVertexWithUV(x + 0.5, y, z + 0.5, lastQuarterU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, halfU, halfV); //The top of the cocoa pod itself
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, halfU, endsV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, endsU, endsV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, endsU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, minU, halfV); //Side towards the log
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, minU, sideV);
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, sideU, sideV);
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, sideU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, minU, halfV); //Side to the left of the log (facing away)
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, minU, sideV);
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, sideU, sideV);
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, sideU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, minU, halfV); //Side facing away from the log
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, minU, sideV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, sideU, sideV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 + halfPixelSeg, sideU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, minU, halfV); //Side to the right of the log (facing away)
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, minU, sideV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, sideU, sideV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels), z + 0.5 - halfPixelSeg, sideU, halfV);
		
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, halfU, halfV); //Bottom of the pod
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 + halfPixelSeg, endsU, halfV);
		tessellator.addVertexWithUV(x + twoPixels + pixelSeg, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, endsU, endsV);
		tessellator.addVertexWithUV(x + twoPixels, y + (1 - twoPixels - doublePixelSeg), z + 0.5 - halfPixelSeg, halfU, endsV);
		return true;
	}
}