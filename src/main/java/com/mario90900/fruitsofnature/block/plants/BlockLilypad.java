package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityLilypadPlant;

public class BlockLilypad extends BlockWaterPlant {
	public BlockLilypad(){
		super();
		this.setBlockName(UnlocalizedNames.LILYPAD_PLANT);
		this.setBlockTextureName(UnlocalizedNames.LILYPAD_PLANT);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.125F, 0.5F + f);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityLilypadPlant();
	}
	
	@Override
	public int getRenderType() {
        return RenderIds.RenderLilypad;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.lilypadSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return Item.getItemFromBlock(Blocks.waterlily);
	}
}
