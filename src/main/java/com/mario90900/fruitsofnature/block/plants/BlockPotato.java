package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityPotatoPlant;

public class BlockPotato extends BlockGroundPlant {
	public BlockPotato(){
		super();
		this.setBlockName(UnlocalizedNames.POTATO_PLANT);
		this.setBlockTextureName(UnlocalizedNames.POTATO_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityPotatoPlant();
	}
	
	@Override
	public int getRenderType() {
        return 6;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.potatoSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.potatoCrop;
	}
}
