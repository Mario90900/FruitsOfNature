package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityPumpkinPlant;

public class BlockPumpkin extends BlockVineGroundPlant {
	public BlockPumpkin(){
		super();
		this.setBlockName(UnlocalizedNames.PUMPKIN_PLANT);
		this.setBlockTextureName(UnlocalizedNames.PUMPKIN_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityPumpkinPlant();
	}
	
	@Override
	public int getRenderType() {
        return RenderIds.RenderPumpkinVine;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.pumpkinSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.pumpkinCrop;
	}
}