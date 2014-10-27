package com.mario90900.fruitsofnature.block.plants;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityCocoaPlant;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCocoa extends BlockLogPlant{
	public BlockCocoa(){
		super();
		this.setBlockName(UnlocalizedNames.COCOA_PLANT);
		this.setBlockTextureName(UnlocalizedNames.COCOA_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityCocoaPlant();
	}

	@Override
	public int getRenderType() {
        return RenderIds.RenderCocoa;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.cocoaSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.cocoaCrop;
	}
}
