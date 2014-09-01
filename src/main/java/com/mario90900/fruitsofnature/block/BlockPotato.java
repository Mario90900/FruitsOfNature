package com.mario90900.fruitsofnature.block;

import java.util.ArrayList;
import java.util.Random;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityPotatoPlant;
import com.mario90900.fruitsofnature.utility.PlantHelper;

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

public class BlockPotato extends BlockPlant implements ITileEntityProvider, IGrowable{
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
	protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.farmland;
    }
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		super.updateTick(world, x, y, z, rand);
		
		if (world.getBlockLightValue(x, y, z) >= 9) {
			int meta = world.getBlockMetadata(x, y, z);
			
			if (meta < 7){
				TileEntityPotatoPlant tile = getPlantTile(world, x, y, z);
				tile.onBlockTick(world, x, y, z, rand);
			}
		}
	}
	
	public int getRenderType() {
        return 6;
    }
	
	protected Item returnSeeds() {
		return ModItems.potatoSeeds;
	}
	
	protected Item returnCrop() {
		return ModItems.potatoCrop;
	}
	
	public static TileEntityPotatoPlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityPotatoPlant))
			return null;

		return (TileEntityPotatoPlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		TileEntityPotatoPlant tilePotato = getPlantTile(world, x, y, z);
		
		if (!(tilePotato == null)){
			this.minDrops = Math.round(tilePotato.calcYield());
			this.maxDrops = Math.round(tilePotato.calcYieldMax());
			
			int numDrops;
			int potency = tilePotato.getPotencyInt();
			int yield = tilePotato.getYieldInt();
			int growth = tilePotato.getGrowthInt();
			
			numDrops = MathHelper.getRandomIntegerInRange(world.rand, minDrops, maxDrops);

			if (metadata >= 7) {
				for (int i = 0; i < 3 + fortune; ++i) {
					if (world.rand.nextInt(15) <= metadata) {
						ItemStack itemSeed = new ItemStack(this.returnSeeds(), 1, 0);
						PlantHelper.setStats(itemSeed, potency, yield, growth);
						ret.add(itemSeed);
					}
				}
				ItemStack itemCrop = new ItemStack(this.returnCrop(), numDrops, 0);
				PlantHelper.setStats(itemCrop, potency, yield, growth);
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