package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityRedMushPlant;

public class BlockRedMush extends BlockMushroomPlant {
	public BlockRedMush(){
		super();
		this.setBlockName(UnlocalizedNames.RED_MUSH_PLANT);
		this.setBlockTextureName(UnlocalizedNames.RED_MUSH_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityRedMushPlant();
	}
	
	@Override
	public int getRenderType() {
        return 6;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.redMushSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.redMushCrop;
	}
}
