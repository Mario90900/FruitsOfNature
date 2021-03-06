package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityWheatPlant;

public class BlockWheat extends BlockGroundPlant {
	public BlockWheat(){
		super();
		this.setBlockName(UnlocalizedNames.WHEAT_PLANT);
		this.setBlockTextureName(UnlocalizedNames.WHEAT_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityWheatPlant();
	}
	
	@Override
	public int getRenderType() {
        return 6;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.wheatSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.wheatCrop;
	}
}
