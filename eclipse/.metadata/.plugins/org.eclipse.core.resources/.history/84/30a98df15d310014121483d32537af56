package com.mario90900.fruitsofnature.block;

import java.util.ArrayList;
import java.util.Random;

import com.mario90900.fruitsofnature.init.ModItems;
import com.mario90900.fruitsofnature.reference.UnlocalizedNames;
import com.mario90900.fruitsofnature.tileentity.TileEntityWheatPlant;
import com.mario90900.fruitsofnature.utility.LogHelper;
import com.mario90900.fruitsofnature.utility.PlantHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.IGrowable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWheat extends BlockPlant implements ITileEntityProvider, IGrowable{ //TODO Deal with the case of if the block below is broken (check that this dosnt happen already, and this can be in a base Ground Plants class).	
	public BlockWheat(){
		super();
		this.setBlockName(UnlocalizedNames.WHEAT_PLANT);
		this.setBlockTextureName(UnlocalizedNames.WHEAT_PLANT);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityWheatPlant();
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
				TileEntityWheatPlant tile = getPlantTile(world, x, y, z);
				tile.onBlockTick(world, x, y, z, rand);
			}
		}
	}
	
	public int getRenderType() {
        return 6;
    }
	
	protected Item returnSeeds() {
		return ModItems.wheatSeeds;
	}
	
	protected Item returnCrop() {
		return ModItems.wheatCrop;
	}
	
	public static TileEntityWheatPlant getPlantTile(IBlockAccess world, int x, int y, int z) {
		TileEntity tile = world.getTileEntity(x, y, z);
		if (!(tile instanceof TileEntityWheatPlant))
			return null;

		return (TileEntityWheatPlant) tile;
	}
	
	@Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		TileEntityWheatPlant tileWheat = getPlantTile(world, x, y, z);
		
		if (!(tileWheat == null)){
			float yieldFloat = tileWheat.calcYield();
			
			if (yieldFloat < 1f){
				this.minDrops = 0;
				this.maxDrops = 1;
			} else {
				this.minDrops = (int) yieldFloat;
				this.maxDrops = (int) yieldFloat;
			}
			
			int numDrops;
			int potency = tileWheat.getPotencyInt();
			int yield = tileWheat.getYieldInt();
			int growth = tileWheat.getGrowthInt();
			
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
