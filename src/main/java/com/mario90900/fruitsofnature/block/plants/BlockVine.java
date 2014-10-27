package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityVinePlant;

public class BlockVine extends BlockWallPlant {
	public BlockVine(){
		super(false);
		this.setBlockName(UnlocalizedNames.VINE_PLANT);
		this.setBlockTextureName(UnlocalizedNames.VINE_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityVinePlant();
	}
	
	@Override
	public int getRenderType() {
        return RenderIds.RenderVine;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.vineSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return Item.getItemFromBlock(Blocks.vine);
	}
}
