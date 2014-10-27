package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityBrownMushPlant;

public class BlockBrownMush extends BlockMushroomPlant {
	public BlockBrownMush(){
		super();
		this.setBlockName(UnlocalizedNames.BROWN_MUSH_PLANT);
		this.setBlockTextureName(UnlocalizedNames.BROWN_MUSH_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityBrownMushPlant();
	}
	
	public int getRenderType() {
        return 6;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.brownMushSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.brownMushCrop;
	}
}
