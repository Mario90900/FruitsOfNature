package com.mario90900.fruitsofnature.block;

import java.util.ArrayList;
import java.util.Random;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.RenderIds;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityVinePlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends BlockPlant implements ITileEntityProvider, IGrowable{
	public BlockVine(){
		super();
		this.setBlockName(UnlocalizedNames.VINE_PLANT);
		this.setBlockTextureName(UnlocalizedNames.VINE_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityVinePlant();
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block) {
        return true;
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		super.updateTick(world, x, y, z, rand);
		
		if (world.getBlockLightValue(x, y, z) >= 9) {
			int meta = world.getBlockMetadata(x, y, z);
			
			if (meta < 7){
				TileEntityVinePlant tile = getPlantTile(world, x, y, z);
				tile.onBlockTick(world, x, y, z, rand);
			}
		}
	}
	
	public boolean growDirection(){ //Intended for use on Wall Plants to determine if they grow upwards (true), or downwards (false)
		return false;
	}
	
	public int getRenderType() {
        return RenderIds.RenderVine;
    }
	
	protected Item returnSeeds() {
		return ModItems.vineSeeds;
	}
	
	protected Item returnCrop() {
		return Item.getItemFromBlock(Blocks.vine);
	}
	
	public static TileEntityVinePlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityVinePlant))
			return null;

		return (TileEntityVinePlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		TileEntityVinePlant tileVine = getPlantTile(world, x, y, z);
		
		if (!(tileVine == null)){
			int numDrops = tileVine.calcYield(world.rand);
			int potency = tileVine.getPotencyInt();
			int yield = tileVine.getYieldInt();
			int growth = tileVine.getGrowthInt();

			if (metadata >= 7) {
				for (int i = 0; i < 3 + fortune; ++i) {
					if (world.rand.nextInt(15) <= metadata) {
						ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
						PlantHelper.setStats(itemSeed, potency, yield, growth);
						ret.add(itemSeed);
					}
				}
				ItemStack itemCrop = new ItemStack(this.returnCrop(), numDrops, 0);
				ret.add(itemCrop);
			} else {
				ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
				PlantHelper.setStats(itemSeed, potency, yield, growth);
				ret.add(itemSeed);
			}
			
			removeTileEnt(world, x, y, z, metadata);
		}

        return ret;
    }
}
