package com.mario90900.fruitsofnature.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityLilypadPlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

public class BlockLilypad extends BlockPlant implements ITileEntityProvider, IGrowable{
	public BlockLilypad(){
		super();
		this.setBlockName(UnlocalizedNames.LILYPAD_PLANT);
		this.setBlockTextureName(UnlocalizedNames.LILYPAD_PLANT);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityLilypadPlant();
	}
	
	@Override
	protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.water;
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		super.updateTick(world, x, y, z, rand);
		
		if (world.getBlockLightValue(x, y, z) >= 6) {
			int meta = world.getBlockMetadata(x, y, z);
			
			if (meta < 7){
				TileEntityLilypadPlant tile = getPlantTile(world, x, y, z);
				tile.onBlockTick(world, x, y, z, rand);
			}
		}
	}
	
	public int getRenderType() {
        return 6;
    }
	
	protected Item returnSeeds() {
		return ModItems.lilypadSeeds;
	}
	
	protected Item returnCrop() {
		return Item.getItemFromBlock(Blocks.waterlily);
	}
	
	public static TileEntityLilypadPlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityLilypadPlant))
			return null;

		return (TileEntityLilypadPlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		TileEntityLilypadPlant tileLilypad = getPlantTile(world, x, y, z);
		
		if (!(tileLilypad == null)){
			float yieldFloat = tileLilypad.calcYield();
			
			if (yieldFloat < 1f){
				this.minDrops = 0;
				this.maxDrops = 1;
			} else {
				this.minDrops = (int) yieldFloat;
				this.maxDrops = (int) yieldFloat;
			}
			
			int numDrops;
			int potency = tileLilypad.getPotencyInt();
			int yield = tileLilypad.getYieldInt();
			int growth = tileLilypad.getGrowthInt();
			
			if (minDrops == maxDrops) {
				numDrops = maxDrops;
			} else {
				numDrops = MathHelper.getRandomIntegerInRange(world.rand, minDrops, maxDrops);
			}

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