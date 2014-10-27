package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityCarrotPlant;

public class BlockCarrot extends BlockGroundPlant {
	public BlockCarrot(){
		super();
		this.setBlockName(UnlocalizedNames.CARROT_PLANT);
		this.setBlockTextureName(UnlocalizedNames.CARROT_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCarrotPlant();
	}
	
	public int getRenderType() {
        return 6;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.carrotSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.carrotCrop;
	}
}
