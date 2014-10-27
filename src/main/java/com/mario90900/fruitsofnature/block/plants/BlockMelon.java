package com.mario90900.fruitsofnature.block.plants;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityMelonPlant;

public class BlockMelon extends BlockVineGroundPlant {
	public BlockMelon(){
		super();
		this.setBlockName(UnlocalizedNames.MELON_PLANT);
		this.setBlockTextureName(UnlocalizedNames.MELON_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityMelonPlant();
	}
	
	@Override
	public int getRenderType() {
        return RenderIds.RenderMelonVine;
    }
	
	@Override
	protected Item returnSeeds() {
		return ModItems.melonSeeds;
	}
	
	@Override
	protected Item returnCrop() {
		return ModItems.melonCrop;
	}
}
